package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpsVuosiluokkakokonaisuusLisatietoDto {
    private Set<Long> piilotetutOppiaineet = new HashSet<>();
}