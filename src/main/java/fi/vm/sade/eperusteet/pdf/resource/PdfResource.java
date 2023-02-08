package fi.vm.sade.eperusteet.pdf.resource;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiOldDto;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController()
@RequestMapping({"/api/pdf"})
@RequiredArgsConstructor
public class PdfResource {

    @Autowired
    DokumenttiService dokumenttiService;

    @GetMapping(path = "/generate/eperusteet/{id}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DokumenttiOldDto> generateEperusteetPdf(@PathVariable("id") Long id,
                                                                  @PathVariable("revision") Integer revision) {
        try {
            Dokumentti dokumentti = dokumenttiService.getDto(id, Kieli.FI, revision, DokumenttiTyyppi.PERUSTE);
            dokumentti = dokumenttiService.generate(dokumentti, null);
            return new ResponseEntity<>(tempOldMapper(dokumentti), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/generate/kvliite/{id}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DokumenttiOldDto> generateKVLiitePdf(@PathVariable("id") Long id,
                                                               @PathVariable("revision") Integer revision) {
        try {
            Dokumentti dokumentti = dokumenttiService.getDto(id, Kieli.FI, revision, DokumenttiTyyppi.KVLIITE);
            dokumentti = dokumenttiService.generate(dokumentti, null);
            return new ResponseEntity<>(tempOldMapper(dokumentti), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/generate/amosaa/{id}/{ktId}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DokumenttiOldDto> generateAmosaaPdf(@PathVariable("id") Long id,
                                                              @PathVariable("ktId") Long ktId,
                                                              @PathVariable("revision") Integer revision) {
        try {
            Dokumentti dokumentti = dokumenttiService.getDto(id, Kieli.FI, revision, DokumenttiTyyppi.OPS);
            dokumentti = dokumenttiService.generate(dokumentti, ktId);
            return new ResponseEntity<>(tempOldMapper(dokumentti), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/generate/ylops/{id}/{revision}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DokumenttiOldDto> generateYlopsPdf(@PathVariable("id") Long id,
                                                             @PathVariable("revision") Integer revision) {
        try {
            Dokumentti dokumentti = dokumenttiService.getDto(id, Kieli.FI, revision, DokumenttiTyyppi.TOTEUTUSSUUNNITELMA);
            dokumentti = dokumenttiService.generate(dokumentti, null);
            return new ResponseEntity<>(tempOldMapper(dokumentti), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @GetMapping(path = "/fetch/{perusteId}/{kieli}/{revision}", produces = "application/pdf")
    @ResponseBody
    public ResponseEntity<Object> getDokumentti(@PathVariable("perusteId") final Long perusteId,
                                                @PathVariable("kieli") final Kieli kieli,
                                                @PathVariable("revision") final Integer revision) {
        // TODO: kesken
        byte[] pdfdata = dokumenttiService.get(perusteId, revision, kieli);
        if (pdfdata == null || pdfdata.length == 0) {
            log.error("Got null or empty data from service");
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
}
