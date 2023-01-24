package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import lombok.Value;

import java.util.Date;

@Value
public class TavoitepaivamaaraDto {
    private String tavoite;
    private Date tapahtumapaivamaara;
}
