package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineenVuosiluokkaKokonaisuusDto implements ReferenceableDto {
    private Long id;

    @JsonAlias({"_vuosiluokkaKokonaisuus", "_vuosiluokkakokonaisuus"})
    private Reference vuosiluokkaKokonaisuus;
    private Optional<TekstiOsaDto> tehtava;
    private Optional<TekstiOsaDto> tyotavat;
    private Optional<TekstiOsaDto> ohjaus;
    private Optional<TekstiOsaDto> arviointi;
    private Optional<TekstiOsaDto> sisaltoalueinfo;
    private Optional<LokalisoituTekstiDto> opetuksenTavoitteetOtsikko;
    private Optional<LokalisoituTekstiDto> vapaaTeksti;
    private List<OpetuksenTavoiteDto> tavoitteet;
    private List<KeskeinenSisaltoalueDto> sisaltoalueet;
    private List<KevytTekstiKappaleDto> vapaatTekstit;
}
