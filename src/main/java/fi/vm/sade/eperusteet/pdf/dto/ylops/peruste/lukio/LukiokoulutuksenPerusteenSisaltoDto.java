package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukiokoulutuksenPerusteenSisaltoDto {
    private LukioPerusteSisaltoDto sisalto;
    private LukioOpetussuunnitelmaRakenneDto rakenne;
    private AihekokonaisuudetDto aihekokonaisuudet;
    private OpetuksenYleisetTavoitteetDto opetuksenYleisetTavoitteet;
}
