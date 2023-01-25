package fi.vm.sade.eperusteet.pdf.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
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
                                            @PathVariable("revision") Integer revision) throws JsonProcessingException, DokumenttiException {

        Dokumentti createDtoFor = dokumenttiService.createDtoFor(
                perusteId,
                Kieli.FI,
                revision);

        if (createDtoFor != null && createDtoFor.getTila() != DokumenttiTila.EPAONNISTUI) {
            dokumenttiService.setStarted(createDtoFor);
            dokumenttiService.generateWithDto(createDtoFor);
//            dokumenttiService = HttpStatus.ACCEPTED;
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/fetch/{perusteId}/{revision}/{kieli}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> fetchPdf(@PathVariable("perusteId") Long perusteId,
                                         @PathVariable("revision") Integer revision,
                                         @PathVariable("kieli") Kieli kieli) {
        return ResponseEntity.ok().build();
    }
}
