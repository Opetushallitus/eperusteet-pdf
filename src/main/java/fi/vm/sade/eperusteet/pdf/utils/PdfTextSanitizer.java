package fi.vm.sade.eperusteet.pdf.utils;

import java.util.Map;

/** PDF-oriented string replacements (e.g. NBSP, soft hyphen next to hyphen). */
public final class PdfTextSanitizer {

    private static final Map<String, String> CHARACTER_REPLACEMENTS = Map.of(
            "&nbsp;", " ",
            "-\u00AD", "-",
            "\u00AD-", "-"
    );

    private PdfTextSanitizer() {
    }

    public static String applyCharacterReplacements(String text) {
        if (text == null) {
            return null;
        }
        String result = text;
        for (Map.Entry<String, String> e : CHARACTER_REPLACEMENTS.entrySet()) {
            result = result.replace(e.getKey(), e.getValue());
        }
        return result;
    }
}
