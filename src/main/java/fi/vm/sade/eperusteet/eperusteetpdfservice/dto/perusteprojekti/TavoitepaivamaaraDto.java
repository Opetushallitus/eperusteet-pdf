package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import lombok.Value;

import java.util.Date;

@Value
public class TavoitepaivamaaraDto {
    private String tavoite;
    private Date tapahtumapaivamaara;
}
