package fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtusivuDto {
    private Long toteutussuunnitelmatKeskeneraiset = 0l;
    private Long toteutussuunnitelmatJulkaistut = 0l;
    private Long ktYhteinenOsuusKeskeneraiset = 0l;
    private Long ktYhteinenOsuusJulkaistut = 0l;
    private Long toteutussuunnitelmaPohjatKeskeneraiset = 0l;
    private Long toteutussuunnitelmaPohjatValmiit = 0l;
}
