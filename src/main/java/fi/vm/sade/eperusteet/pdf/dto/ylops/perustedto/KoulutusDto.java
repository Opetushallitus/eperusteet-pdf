package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KoulutusDto {
    private PerusteenLokalisoituTekstiDto nimi;
    private String koulutuskoodiArvo;
    private String koulutuskoodiUri;
    private String koulutusalakoodi;
    private String opintoalakoodi;
}
