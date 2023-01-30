package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.SuorituspolkuRiviDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RakenneModuuliTunnisteDto extends RakenneModuuliDto{

    private Set<String> koodit;

    public static RakenneModuuliTunnisteDto of(AbstractRakenneOsaDto rakenneModuuliDto, SuorituspolkuRiviDto suorituspolkuRiviDto) {

        RakenneModuuliTunnisteDto rakenneModuuliTunnisteDto = new RakenneModuuliTunnisteDto();
        BeanUtils.copyProperties(rakenneModuuliDto, rakenneModuuliTunnisteDto, "osat");

        if(suorituspolkuRiviDto != null) {
            rakenneModuuliTunnisteDto.setKuvaus(suorituspolkuRiviDto.getKuvaus());
            rakenneModuuliTunnisteDto.setKoodit(suorituspolkuRiviDto.getKoodit());
        }

        rakenneModuuliTunnisteDto.setOsat(new ArrayList<>());

        return rakenneModuuliTunnisteDto;
    }
}
