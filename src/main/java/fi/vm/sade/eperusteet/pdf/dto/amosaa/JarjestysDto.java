package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JarjestysDto {
    Long id;
    List<Long> lisaIdt;
    Integer jnro;
}
