package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaKevytDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OsaamisenArvioinninToteutussuunnitelmaDto {
    private Long id;
    private OpetussuunnitelmaKevytDto opetussuunnitelma;
    private OpetussuunnitelmaKevytDto oatOpetussuunnitelma;
    private LokalisoituTekstiDto nimi;
    private Map<Kieli, String> url;
}