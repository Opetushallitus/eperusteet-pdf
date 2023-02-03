package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteTekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioPerusteOppiaineDto implements PerusteenOsa {
    private Long id;
    private UUID tunniste;
    private String koodiUri;
    private String koodiArvo;
    private boolean koosteinen;
    private Integer jarjestys;
    private Boolean abstrakti;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto pakollinenKurssiKuvaus;
    private LokalisoituTekstiDto syventavaKurssiKuvaus;
    private LokalisoituTekstiDto soveltavaKurssiKuvaus;
    private PerusteTekstiOsaDto tehtava;
    private PerusteTekstiOsaDto tavoitteet;
    private PerusteTekstiOsaDto arviointi;
    private Set<LukioPerusteOppiaineDto> oppimaarat = new HashSet<>();
    private Set<LukiokurssiPerusteDto> kurssit = new HashSet<>();

    @Override
    public Stream<? extends PerusteenOsa> osat() {
        return Stream.concat(oppimaarat.stream(), kurssit.stream());
    }

    @JsonIgnore
    public Map<LukiokurssiTyyppi, Optional<LokalisoituTekstiDto>> getKurssiTyyppiKuvaukset() {
        Map<LukiokurssiTyyppi, Optional<LokalisoituTekstiDto>> map = new HashMap<>();
        map.put(LukiokurssiTyyppi.VALTAKUNNALLINEN_PAKOLLINEN, Optional.ofNullable(pakollinenKurssiKuvaus));
        map.put(LukiokurssiTyyppi.VALTAKUNNALLINEN_SYVENTAVA, Optional.ofNullable(syventavaKurssiKuvaus));
        map.put(LukiokurssiTyyppi.VALTAKUNNALLINEN_SOVELTAVA, Optional.ofNullable(soveltavaKurssiKuvaus));
        return map;
    }

    public Stream<LukioPerusteOppiaineDto> maarat() {
        return oppimaarat.stream();
    }

    public Stream<LukioPerusteOppiaineDto> maarineen() {
        return Stream.concat(Stream.of(this), maarat().flatMap(LukioPerusteOppiaineDto::maarineen));
    }
}
