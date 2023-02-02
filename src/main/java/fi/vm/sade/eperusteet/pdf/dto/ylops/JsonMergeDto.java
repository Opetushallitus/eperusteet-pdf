package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonMergeDto<A, B> {
    @JsonUnwrapped
    private A a;

    @JsonUnwrapped
    private B b;
}
