package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalauteDto {
    private Integer stars;
    private String feedback;

    @JsonProperty("user-agent")
    private String userAgent;

    @JsonProperty("created-at")
    private Date createdAt;
    private String key;
    private String data;

    public void setUser_agent(String userAgent) {
        this.userAgent = userAgent;
    }
}
