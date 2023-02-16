package fi.vm.sade.eperusteet.pdf.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

// Cache haluaa, että on Serializable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LokalisointiDto implements Serializable {
    String value;
    String key;
    Long id;
    String locale;
    String description;
    String category;
    Date created;
}
