package fi.vm.sade.eperusteet.pdf.resource;

import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping({"/api/pdf"})
@RequiredArgsConstructor
public class PdfResource {
    @Autowired
    DokumenttiService dokumenttiService;

    @PostMapping(path = "/generate/eperusteet/{dokumenttiId}/{kieli}/{versio}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateEperusteetPdf(@PathVariable("dokumenttiId") Long dokumenttiId,
                                                        @PathVariable("kieli") Kieli kieli,
                                                        @PathVariable("versio") GeneratorVersion versio,
                                                        @RequestBody String perusteJson) {
        try {
            dokumenttiService.generateForEperusteet(dokumenttiId, kieli, versio, perusteJson);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(path = "/generate/amosaa/{id}/{revision}/{kieli}/{ktId}", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> generateAmosaaPdf(@PathVariable("id") Long id,
//                                                    @PathVariable("revision") Integer revision,
//                                                    @PathVariable("kieli") Kieli kieli,
//                                                    @PathVariable("ktId") Long ktId) {
//        try {
//            byte[] pdfData = dokumenttiService.generate(id, revision, kieli, DokumenttiTyyppi.OPS, ktId);
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(path = "/generate/ylops/{id}/{revision}/{kieli}", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> generateYlopsPdf(@PathVariable("id") Long id,
//                                                   @PathVariable("revision") Integer revision,
//                                                   @PathVariable("kieli") Kieli kieli) {
//        try {
//            byte[] pdfData = dokumenttiService.generate(id, revision, kieli, DokumenttiTyyppi.TOTEUTUSSUUNNITELMA, null);
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
