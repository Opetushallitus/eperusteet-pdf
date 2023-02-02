package fi.vm.sade.eperusteet.pdf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LokalisointiDto {
    String value;
    String key;
    Long id;
    String locale;
    String description;
    String category;
    Date created;
}
