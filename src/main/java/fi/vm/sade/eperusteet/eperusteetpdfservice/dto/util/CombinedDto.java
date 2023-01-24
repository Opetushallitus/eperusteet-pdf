package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nkala
 * @param <A> First dto to be combined
 * @param <B> Second dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedDto<A, B> {
    @JsonUnwrapped
    private A a;

    @JsonUnwrapped
    private B b;
}
