package fi.vm.sade.eperusteet.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdfData {
    private String data;
    private String html;
    private String tila;
}
