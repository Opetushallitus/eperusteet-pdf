package fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteKeskeinensisaltoalueDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteTekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteAIPEOppiaineSisaltoDto {
    private Long id;
    private UUID tunniste;
    private LokalisoituTekstiDto nimi;
    private Boolean koosteinen;
    private Boolean abstrakti;
    private KoodiDto koodi;
    private PerusteTekstiOsaDto tehtava;
    private PerusteTekstiOsaDto arviointi;
    private PerusteTekstiOsaDto tyotavat;
    private PerusteTekstiOsaDto ohjaus;
    private PerusteTekstiOsaDto sisaltoalueinfo;
    private LokalisoituTekstiDto pakollinenKurssiKuvaus;
    private LokalisoituTekstiDto syventavaKurssiKuvaus;
    private LokalisoituTekstiDto soveltavaKurssiKuvaus;
    private LokalisoituTekstiDto vapaaTeksti;
    private List<KevytTekstiKappaleDto> vapaatTekstit = new ArrayList<>();
    private List<PerusteAIPEOpetuksentavoiteSisaltoDto> tavoitteet = new ArrayList<>();
    private List<PerusteKeskeinensisaltoalueDto> sisaltoalueet = new ArrayList<>();
}
