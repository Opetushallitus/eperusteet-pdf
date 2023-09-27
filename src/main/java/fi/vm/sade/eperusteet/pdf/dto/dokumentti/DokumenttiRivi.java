package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiRiviTyyppi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DokumenttiRivi {

    private ArrayList<String> sarakkeet = new ArrayList<>();
    private Integer colspan = 1;
    private DokumenttiRiviTyyppi tyyppi = DokumenttiRiviTyyppi.DEFAULT;

    public void addSarake(String... sarakkeet) {
        this.sarakkeet.addAll(Arrays.asList(sarakkeet));
    }

    public String getBackgroundColor() {
        switch (tyyppi) {
            case HEADER:
                return DokumenttiRiviBgColor.HEADER;
            case SUBHEADER:
                return DokumenttiRiviBgColor.SUBHEADER;
        }

        return DokumenttiRiviBgColor.DEFAULT;
    }

    public String getFontColor() {
        switch (tyyppi) {
            case HEADER:
                return DokumenttiRiviFontColor.HEADER;
        }

        return DokumenttiRiviFontColor.DEFAULT;
    }

    public String getElementType() {
        switch (tyyppi) {
            case HEADER:
                return "th";
        }

        return "td";
    }

    public String getElementAlign() {
        return "start";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        sarakkeet.forEach((sarake) -> {
            builder.append(String.format("<%s colspan=\"%d\" align=\"%s\">", getElementType(), getColspan(), getElementAlign()));
            builder.append(sarake);
            builder.append(String.format("</%s>", getElementType()));
        });
        return builder.toString();
    }
}
