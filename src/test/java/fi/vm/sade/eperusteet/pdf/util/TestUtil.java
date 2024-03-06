package fi.vm.sade.eperusteet.pdf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class TestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getTestJulkaisuJsonAsString(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        ObjectNode julkaisuFile = objectMapper.readValue(resource.getFile(), ObjectNode.class);
        return julkaisuFile.toString();
    }
}
