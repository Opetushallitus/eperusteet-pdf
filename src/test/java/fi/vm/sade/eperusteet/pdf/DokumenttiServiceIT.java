package fi.vm.sade.eperusteet.pdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class DokumenttiServiceIT {

    @Autowired
    private DokumenttiService dokumenttiService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void generatePerustePdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForEperusteet(1L, Kieli.FI, getTestJulkaisuJsonAsString("material/peruste.json")));
    }

    @Test
    public void generateKvLiitePdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForEperusteetKvLiite(1L, Kieli.FI, getTestJulkaisuJsonAsString("material/peruste.json")));
    }

    @Test
    public void generateAmosaaPdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForAmosaa(1L, Kieli.FI, 1L, getTestJulkaisuJsonAsString("material/amosaa.json")));
    }

    @Test
    public void generateYlopsPdf() {
        assertDoesNotThrow(() -> dokumenttiService.generateForYlops(1L, Kieli.FI, getTestJulkaisuJsonAsString("material/ylops.json")));
    }

    private String getTestJulkaisuJsonAsString(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        ObjectNode julkaisuFile = objectMapper.readValue(resource.getFile(), ObjectNode.class);
        return julkaisuFile.toString();
    }
}
