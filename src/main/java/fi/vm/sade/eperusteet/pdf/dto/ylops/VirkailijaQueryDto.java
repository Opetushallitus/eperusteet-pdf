package fi.vm.sade.eperusteet.pdf.dto.ylops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirkailijaQueryDto {
    private Set<String> oid = new HashSet<>();
}
