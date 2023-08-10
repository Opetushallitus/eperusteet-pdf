package fi.vm.sade.eperusteet.pdf.dto.eperusteet.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedDto<A, B> {
    @JsonUnwrapped
    private A a;

    @JsonUnwrapped
    private B b;
}
