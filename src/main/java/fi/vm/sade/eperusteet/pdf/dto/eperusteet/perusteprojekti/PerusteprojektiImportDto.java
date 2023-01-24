package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.liite.LiiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TermiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteprojektiImportDto {
    private PerusteprojektiLuontiDto projekti;
    private PerusteKaikkiDto peruste;
    List<TermiDto> termit;
    HashMap<UUID, byte[]> liitetiedostot;
    List<LiiteDto> liitteet;
}
