package fi.vm.sade.eperusteet.pdf;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import fi.vm.sade.eperusteet.pdf.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class DokumenttiServiceIT {

    @Autowired
    private DokumenttiService dokumenttiService;

    @Test
    public void generatePerustePdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForEperusteet(1L, Kieli.FI, TestUtil.getTestJulkaisuJsonAsString("material/peruste.json")));
    }

    @Test
    public void generateKvLiitePdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForEperusteetKvLiite(1L, Kieli.FI, TestUtil.getTestJulkaisuJsonAsString("material/peruste.json")));
    }

    @Test
    public void generateAmosaaPdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForAmosaa(1L, Kieli.FI, 1L, TestUtil.getTestJulkaisuJsonAsString("material/amosaa.json")));
    }

    @Test
    public void generateYlopsPdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForYlops(1L, Kieli.FI, TestUtil.getTestJulkaisuJsonAsString("material/ylops.json")));
    }
}
