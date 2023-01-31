package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.ProjektiTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.validointi.Validointi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.NavigationNodeDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti.PerusteprojektiListausDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TilaUpdateStatus extends TilaUpdateStatusBuilder {
    @Getter
    @Setter
    private PerusteprojektiListausDto perusteprojekti;

    @Getter
    @Setter
    private Date lastCheck;

    @Getter
    @Setter
    List<Status> infot = new ArrayList<>();

    @Getter
    @Setter
    boolean vaihtoOk = true;

    public TilaUpdateStatus() {
        status = this;
    }

    public void merge(TilaUpdateStatus other) {
        if (other != null) {
            vaihtoOk = vaihtoOk && other.isVaihtoOk();
            infot.addAll(other.infot);
        }
    }

    @Data
    @NoArgsConstructor
    public static class TilaUpdateStatusBuilderForSuoritustapa {
        private TilaUpdateStatusBuilder builder;
        @Getter
        private Suoritustapakoodi suoritustapa;

        public TilaUpdateStatusBuilderForSuoritustapa(TilaUpdateStatusBuilder builder, Suoritustapakoodi suoritustapa) {
            this.builder = builder;
            this.suoritustapa = suoritustapa;
        }

        public TilaUpdateStatusBuilderForSuoritustapa addErrorStatus(String viesti, LokalisoituTekstiDto... tekstit) {
            if (isUsed()) {
                builder.addErrorStatus(viesti, suoritustapa, tekstit);
            }
            return this;
        }

        public void addErrorStatusForAll(String viesti,
                                         Supplier<Stream<LokalisoituTekstiDto>> all) {
            if (isUsed()) {
                LokalisoituTekstiDto[] tekstit = all.get().toArray(LokalisoituTekstiDto[]::new);
                if (tekstit.length > 0) {
                    builder.addErrorStatus(viesti, suoritustapa, tekstit);
                }
            }
        }

        public TilaUpdateStatusBuilderForSuoritustapa addErrorGiven(String viesti, boolean given) {
            if (given) {
                builder.addErrorStatus(viesti, suoritustapa);
            }
            return this;
        }

        public TilallinenTilaUpdateStatusBuilderForSuoritustapa toTila(ProjektiTila tila) {
            return new TilallinenTilaUpdateStatusBuilderForSuoritustapa(this.builder, this.suoritustapa, tila);
        }

        protected boolean isUsed() {
            return true;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TilallinenTilaUpdateStatusBuilderForSuoritustapa extends TilaUpdateStatusBuilderForSuoritustapa {
        private final ProjektiTila targetTila;
        private Set<ProjektiTila> tilat = new HashSet<>();

        public TilallinenTilaUpdateStatusBuilderForSuoritustapa(TilaUpdateStatusBuilder builder, Suoritustapakoodi suoritustapa,
                                                      ProjektiTila targetTila) {
            super(builder, suoritustapa);
            this.targetTila = targetTila;
        }

        @Override
        public boolean isUsed() {
            return tilat.isEmpty() || tilat.contains(targetTila);
        }

        public TilallinenTilaUpdateStatusBuilderForSuoritustapa forTilat(ProjektiTila ...tilat) {
            this.tilat = new HashSet<>(asList(tilat));
            return this;
        }

        public TilallinenTilaUpdateStatusBuilderForSuoritustapa check(
                        Consumer<TilallinenTilaUpdateStatusBuilderForSuoritustapa> status) {
            if (isUsed()) {
                status.accept(this);
            }
            return this;
        }

        @Override
        public TilallinenTilaUpdateStatusBuilderForSuoritustapa addErrorStatus(String viesti, LokalisoituTekstiDto... tekstit) {
            super.addErrorStatus(viesti, tekstit);
            return this;
        }
        @Override
        public void addErrorStatusForAll(String viesti,
                                         Supplier<Stream<LokalisoituTekstiDto>> all) {
            super.addErrorStatusForAll(viesti, all);
        }
        @Override
        public TilallinenTilaUpdateStatusBuilderForSuoritustapa addErrorGiven(String viesti, boolean given) {
            super.addErrorGiven(viesti, given);
            return this;
        }
    }

    public TilaUpdateStatusBuilderForSuoritustapa forSuoritustapa(Suoritustapakoodi suoritustapa) {
        return new TilaUpdateStatusBuilderForSuoritustapa(this, suoritustapa);
    }

    public void addStatus(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet) {
        if (infot == null) {
            infot = new ArrayList<>();
        }
        infot.add(new Status(viesti, suoritustapa, validointi, nimet));
    }

    public void addStatus(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet) {
        if (infot == null) {
            infot = new ArrayList<>();
        }
        infot.add(new Status(viesti, suoritustapa, validointi, nimet, kielet));
    }

    public void addStatus(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet, ValidointiKategoria validointiKategoria) {
        if (infot == null) {
            infot = new ArrayList<>();
        }
        infot.add(new Status(viesti, suoritustapa, validointi, nimet, kielet, validointiKategoria));
    }

    public void addStatus(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet, ValidointiKategoria validointiKategoria, ValidointiStatusType validointiStatusType) {
        if (infot == null) {
            infot = new ArrayList<>();
        }
        infot.add(new Status(viesti, suoritustapa, validointi, nimet, kielet, validointiKategoria, validointiStatusType));
    }

    public void addStatus(String viesti, ValidointiKategoria validointiKategoria, LokalisoituTekstiDto nimi) {
        if (infot == null) {
            infot = new ArrayList<>();
        }
        if (infot.stream().anyMatch(info -> info.getViesti().equals(viesti))) {
            infot.stream()
                    .filter(info -> info.getViesti().equals(viesti))
                    .forEach(info -> info.getNimet().add(nimi));
        } else {
            infot.add(Status.builder()
                    .viesti(viesti)
                    .validointiKategoria(validointiKategoria)
                    .nimet(new ArrayList<>(Arrays.asList(nimi)))
                    .validointiStatusType(ValidointiStatusType.VIRHE)
                .build());
        }

    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Status {
        String viesti;
        PerusteprojektiListausDto perusteprojekti;
        Date lastCheck;
        Validointi validointi;
        List<LokalisoituTekstiDto> nimet = new ArrayList<>();
        Suoritustapakoodi suoritustapa;
        Set<Kieli> kielet;
        ValidointiKategoria validointiKategoria = ValidointiKategoria.MAARITTELEMATON;
        ValidointiStatusType validointiStatusType = ValidointiStatusType.VIRHE;
        NavigationNodeDto navigationNode;

        public Status() {
        }

        public Status(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet) {
            this.viesti = viesti;
            this.validointi = validointi;
            this.nimet = nimet;
            this.suoritustapa = suoritustapa;
        }

        public Status(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet) {
            this.viesti = viesti;
            this.validointi = validointi;
            this.nimet = nimet;
            this.suoritustapa = suoritustapa;
            this.kielet = kielet;
        }

        public Status(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet, ValidointiKategoria validointiKategoria) {
            this.viesti = viesti;
            this.validointi = validointi;
            this.nimet = nimet;
            this.suoritustapa = suoritustapa;
            this.kielet = kielet;
            this.validointiKategoria = validointiKategoria;
            this.navigationNode = kategoriaNavigationNode(validointiKategoria);
        }

        public Status(String viesti, Suoritustapakoodi suoritustapa, Validointi validointi, List<LokalisoituTekstiDto> nimet, Set<Kieli> kielet, ValidointiKategoria validointiKategoria, ValidointiStatusType validointiStatusType) {
            this.viesti = viesti;
            this.validointi = validointi;
            this.nimet = nimet;
            this.suoritustapa = suoritustapa;
            this.kielet = kielet;
            this.validointiKategoria = validointiKategoria;
            this.validointiStatusType = validointiStatusType;
            this.navigationNode = kategoriaNavigationNode(validointiKategoria);
        }

        private NavigationNodeDto kategoriaNavigationNode(ValidointiKategoria validointiKategoria) {
            if (validointiKategoria == null) {
                return null;
            }

            switch (validointiKategoria) {
                case PERUSTE:
                    return NavigationNodeDto.of(NavigationType.tiedot);
                case RAKENNE:
                    return NavigationNodeDto.of(NavigationType.muodostuminen);
                default:
                    return null;
            }
        }
    }

}
