package fi.vm.sade.eperusteet.pdf.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiOldDto;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({"/api/pdf"})
@RequiredArgsConstructor
public class PdfResource {

    private static final Logger LOG = LoggerFactory.getLogger(PdfResource.class);

    @Autowired
    DokumenttiService dokumenttiService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping(path = "/generate/{perusteId}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DokumenttiOldDto> generatePdf(@PathVariable("perusteId") Long perusteId,
                                                        @PathVariable("revision") Integer revision) throws JsonProcessingException, DokumenttiException {

        Dokumentti createDtoFor = dokumenttiService.createDtoFor(
                perusteId,
                Kieli.FI,
                revision);

        if (createDtoFor != null && createDtoFor.getTila() != DokumenttiTila.EPAONNISTUI) {
            dokumenttiService.setStarted(createDtoFor);
            dokumenttiService.generateWithDto(createDtoFor);
        }
        return new ResponseEntity<>(tempOldMapper(createDtoFor), HttpStatus.ACCEPTED);
    }

//    @GetMapping(path = "/latest/{perusteId}/{revision}/{kieli}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<DokumenttiOldDto> getLatestDokumentti(@RequestParam("perusteId") final Long perusteId,
//                                                             @RequestParam("kieli") final Kieli kieli,
//                                                             @RequestParam("revision") final Integer revision) {
//        try {
//            Dokumentti dto = dokumenttiService.findLatest(perusteId, revision, kieli);
//
//            return new ResponseEntity<>(tempOldMapper(dto), HttpStatus.OK);
//        } catch (IllegalArgumentException ex) {
//            LOG.warn("{}", ex.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(path = "/fetch/{perusteId}/{kieli}/{revision}", produces = "application/pdf")
    @ResponseBody
    public ResponseEntity<Object> getDokumentti(@PathVariable("perusteId") final Long perusteId,
                                                @PathVariable("kieli") final Kieli kieli,
                                                @PathVariable("revision") final Integer revision) {
        // TODO: kesken
        byte[] pdfdata = dokumenttiService.get(perusteId, revision, kieli);
        if (pdfdata == null || pdfdata.length == 0) {
            LOG.error("Got null or empty data from service");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-disposition", "inline; filename=\"" + "temp.pdf\"");
        return new ResponseEntity<>(pdfdata, headers, HttpStatus.OK);
    }

    //TODO: metodi vain devausta varten, tuupataan kälille vanha dto
    private DokumenttiOldDto tempOldMapper(Dokumentti newDokumentti) {
        DokumenttiOldDto old = new DokumenttiOldDto();
        old.setPerusteId(newDokumentti.getSisaltoId());
        old.setId(newDokumentti.getId());
        old.setKieli(newDokumentti.getKieli());
        return old;
    }
}
