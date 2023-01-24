package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteInfoDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.SuoritustapaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.perusteprojekti.PerusteprojektiKevytDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutkinnonOsaViiteKontekstiDto extends TutkinnonOsaViiteDto {
    PerusteInfoDto peruste;
    SuoritustapaDto suoritustapa;
    PerusteprojektiKevytDto perusteProjekti;
}
