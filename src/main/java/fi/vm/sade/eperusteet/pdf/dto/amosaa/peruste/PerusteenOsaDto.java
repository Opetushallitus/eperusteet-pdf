package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PerusteenOsaDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private LokalisoituTekstiDto nimi;
    private PerusteTila tila;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PerusteenOsaTunniste tunniste;

    public PerusteenOsaDto() {
    }

    public PerusteenOsaDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        this.nimi = nimi;
        this.tila = tila;
        this.tunniste = tunniste;
    }

    @JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "osanTyyppi")
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(value = TekstiKappaleDto.class),
            @JsonSubTypes.Type(value = TutkinnonOsaDto.class),
            @JsonSubTypes.Type(value = OpintokokonaisuusDto.class),
            @JsonSubTypes.Type(value = KoulutuksenOsaDto.class),
            @JsonSubTypes.Type(value = TuvaLaajaAlainenOsaaminenDto.class),
            @JsonSubTypes.Type(value = KotoKielitaitotasoDto.class),
            @JsonSubTypes.Type(value = KotoLaajaAlainenOsaaminenDto.class),
            @JsonSubTypes.Type(value = KotoOpintoDto.class),
    })
    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class Laaja extends PerusteenOsaDto {
        public abstract String getOsanTyyppi();

        public Laaja() {
        }

        public Laaja(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
            super(nimi, tila, tunniste);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Suppea extends PerusteenOsaDto {
        private String osanTyyppi;

        public Suppea() {
        }

        public Suppea(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
            super(nimi, tila, tunniste);
        }
    }

}
