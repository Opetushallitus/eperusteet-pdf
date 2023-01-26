package fi.vm.sade.eperusteet.pdf.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController()
@RequestMapping({"/api/pdf"})
@RequiredArgsConstructor
public class PdfResource {

    private static final Logger LOG = LoggerFactory.getLogger(PdfResource.class);

    @Autowired
    DokumenttiService dokumenttiService;

    @GetMapping(path = "/generate/{perusteId}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dokumentti> generatePdf(@PathVariable("perusteId") Long perusteId,
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
        return new ResponseEntity<>(createDtoFor, HttpStatus.ACCEPTED);
//        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/fetch/{perusteId}/{revision}/{kieli}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> fetchPdf(@PathVariable("perusteId") Long perusteId,
                                         @PathVariable("revision") Integer revision,
                                         @PathVariable("kieli") Kieli kieli) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, params = "perusteId")
    @ResponseBody
    public ResponseEntity<DokumenttiDto> getLatestDokumentti(
            @RequestParam("perusteId") final Long perusteId,
            @RequestParam(defaultValue = "fi") final String kieli,
            @RequestParam("suoritustapa") final String suoritustapa,
            @RequestParam(defaultValue = "") final String version
    ) {
        try {
            Kieli kielikoodi = Kieli.of(kieli);
            Suoritustapakoodi suoritustapakoodi = Suoritustapakoodi.of(suoritustapa);
            GeneratorVersion gversion = null;
            if (!"".equals(version)) {
                gversion = GeneratorVersion.of(version);
            }
//            Dokumentti dto = dokumenttiService.findLatest(perusteId, kielikoodi, suoritustapakoodi, gversion);

            return new ResponseEntity<>(new DokumenttiDto(), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            LOG.warn("{}", ex.getMessage());

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/fetch/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
//    @CacheControl(age = CacheControl.ONE_YEAR, nonpublic = false)
    public ResponseEntity<Object> getDokumentti(
            @PathVariable("fileName") String fileName
    ) {
        Long dokumenttiId = Long.valueOf(FilenameUtils.removeExtension(fileName));
        String extension = FilenameUtils.getExtension(fileName);

        byte[] pdfdata = dokumenttiService.get(dokumenttiId);

        // Tarkistetaan tiedostopääte jos asetettu kutsuun
        if (!ObjectUtils.isEmpty(extension) && !Objects.equals(extension, "pdf")) {
            LOG.error("Got wrong file extension");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (pdfdata == null || pdfdata.length == 0) {
            LOG.error("Got null or empty data from service");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
//        DokumenttiDto dokumenttiDto = service.query(dokumenttiId);
//        Peruste peruste = perusteRepository.findOne(dokumenttiDto.getPerusteId());
//        if (peruste != null) {
//            TekstiPalanen nimi = peruste.getNimi();
//            if (nimi != null && nimi.getTeksti().containsKey(dokumenttiDto.getKieli())) {
//                headers.set("Content-disposition", "inline; filename=\""
//                        + nimi.getTeksti().get(dokumenttiDto.getKieli()) + ".pdf\"");
//            } else {
//                headers.set("Content-disposition", "inline; filename=\"" + dokumenttiId + ".pdf\"");
//            }
//        }
        headers.set("Content-disposition", "inline; filename=\"" + dokumenttiId + ".pdf\"");
        return new ResponseEntity<>(pdfdata, headers, HttpStatus.OK);
    }
}
