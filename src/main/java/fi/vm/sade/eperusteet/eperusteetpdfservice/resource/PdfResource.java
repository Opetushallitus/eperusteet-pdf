package fi.vm.sade.eperusteet.eperusteetpdfservice.resource;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.service.DokumenttiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({"/api/pdf"})
@RequiredArgsConstructor
public class PdfResource {

    private static final Logger LOG = LoggerFactory.getLogger(PdfResource.class);

    @Autowired
    DokumenttiService dokumenttiService;

    @GetMapping(path = "/generate/{perusteId}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> generatePdf(@PathVariable("perusteId") Long perusteId,
                                            @PathVariable("revision") Integer revision) {

//        HttpStatus status = HttpStatus.BAD_REQUEST;
//
//        final DokumenttiDto createDtoFor = dokumenttiService.createDtoFor(
//                perusteId,
//                Kieli.of(kieli),
//                Suoritustapakoodi.of(suoritustapakoodi),
//                GeneratorVersion.of(version));
//        if (createDtoFor != null && createDtoFor.getTila() != DokumenttiTila.EPAONNISTUI) {
//            service.setStarted(createDtoFor);
//            service.generateWithDto(createDtoFor);
//            status = HttpStatus.ACCEPTED;
//        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/fetch/{perusteId}/{revision}/{kieli}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> fetchPdf(@PathVariable("perusteId") Long perusteId,
                                         @PathVariable("revision") Integer revision,
                                         @PathVariable("kieli") Kieli kieli) {




        return ResponseEntity.ok().build();
    }
}
