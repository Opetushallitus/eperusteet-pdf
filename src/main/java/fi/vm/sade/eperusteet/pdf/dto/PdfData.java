package fi.vm.sade.eperusteet.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfData {
    private String data;
    private String tila;

    public static PdfData of(String data){
        return new PdfData(data, null);
    }
}
