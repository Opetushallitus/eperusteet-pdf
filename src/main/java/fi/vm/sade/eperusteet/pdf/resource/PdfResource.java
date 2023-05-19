package fi.vm.sade.eperusteet.pdf.resource;

import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping({"/api/pdf"})
public class PdfResource {

    @Autowired
    DokumenttiService dokumenttiService;

    @PostMapping(path = "/generate/eperusteet/{dokumenttiId}/{kieli}/{versio}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateEperusteetPdf(@PathVariable("dokumenttiId") Long dokumenttiId,
                                                        @PathVariable("kieli") Kieli kieli,
                                                        @PathVariable("versio") GeneratorVersion versio,
                                                        @RequestBody String perusteJson) {
        dokumenttiService.generateForEperusteet(dokumenttiId, kieli, versio, perusteJson);
    }

    @PostMapping(path = "/generate/amosaa/{id}/{kieli}/{ktId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateAmosaaPdf(@PathVariable("id") Long id,
                                                    @PathVariable("kieli") Kieli kieli,
                                                    @PathVariable("ktId") Long ktId,
                                                    @RequestBody String opsJson) {
        dokumenttiService.generateForAmosaa(id, kieli, ktId, opsJson);
    }

    @PostMapping(path = "/generate/ylops/{id}/{kieli}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateYlopsPdf(@PathVariable("id") Long id,
                                                   @PathVariable("kieli") Kieli kieli,
                                                   @RequestBody String opsJson) {
        dokumenttiService.generateForYlops(id, kieli, opsJson);
    }
}
