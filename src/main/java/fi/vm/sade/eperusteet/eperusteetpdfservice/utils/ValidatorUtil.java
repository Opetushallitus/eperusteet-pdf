package fi.vm.sade.eperusteet.eperusteetpdfservice.utils;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.TutkintonimikeKoodiDto;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
@UtilityClass
public class ValidatorUtil {

    public static boolean hasValidTutkintonimikkeet(PerusteDto peruste, List<TutkintonimikeKoodiDto> tutkintonimikeKoodiDtos) {
        for (TutkintonimikeKoodiDto tutkintonimike : tutkintonimikeKoodiDtos) {
            boolean hasTutkintonimikkeetPerusteenKielilla = peruste.getKielet().stream()
                    .allMatch(kieli -> tutkintonimike.getNimi() != null && StringUtils.isNotEmpty(tutkintonimike.getNimi().get(kieli)));
            if (!hasTutkintonimikkeetPerusteenKielilla) {
                return false;
            }
        }
        return true;
    }
}
