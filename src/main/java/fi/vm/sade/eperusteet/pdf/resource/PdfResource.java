package fi.vm.sade.eperusteet.pdf.resource;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController()
@RequestMapping({"/api/pdf"})
public class PdfResource {

    @Autowired
    DokumenttiService dokumenttiService;

    @Autowired
    ThreadPoolTaskExecutor docTaskExecutor;

    @PostMapping(path = "/generate/eperusteet/{dokumenttiId}/{kieli}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateEperusteetPdf(@PathVariable("dokumenttiId") Long dokumenttiId,
                                                        @PathVariable("kieli") Kieli kieli,
                                                        @RequestBody String perusteJson) {
        dokumenttiService.generateForEperusteet(dokumenttiId, kieli, perusteJson);
    }

    @PostMapping(path = "/generate/eperusteet/{dokumenttiId}/{kieli}/kvliite")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateEperusteetKvLiitePdf(@PathVariable("dokumenttiId") Long dokumenttiId,
                                      @PathVariable("kieli") Kieli kieli,
                                      @RequestBody String perusteJson) {
        dokumenttiService.generateForEperusteetKvLiite(dokumenttiId, kieli, perusteJson);
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

    @GetMapping("/info")
    public Object getExecutor() {
        return Map.of(
                "active", docTaskExecutor.getActiveCount(),
                "queue", docTaskExecutor.getQueueSize()
        );
    }

}
