package fi.vm.sade.eperusteet.pdf.domain.validointi;

import com.google.common.base.CharMatcher;
import fi.vm.sade.eperusteet.pdf.domain.TekstiPalanen;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public abstract class ValidHtmlValidatorBase {

    private Whitelist whitelist;
    private UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
    private EmailValidator emailValidator = EmailValidator.getInstance(true, true);

    protected void setupValidator(ValidHtml constraintAnnotation) {
        whitelist = constraintAnnotation.whitelist().getWhitelist();
    }

    protected boolean isValid(TekstiPalanen palanen) {
        if (palanen != null && palanen.getTeksti() != null && !palanen.getTeksti().isEmpty()) {
            for (Kieli kieli : palanen.getTeksti().keySet()) {
                if (!Jsoup.isValid(palanen.getTeksti().get(kieli), whitelist)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Deprecated
    private boolean isValidUrls(String teksti) {
        Document doc = Jsoup.parse(teksti);
        Elements links = doc.select("a[href]");
        return links.stream().allMatch(link ->
                !link.attr("routenode").isEmpty()
                        || urlValidator.isValid(CharMatcher.whitespace().trimFrom(link.attr("abs:href")))
                        || emailValidator.isValid(CharMatcher.whitespace().trimFrom(link.attr("href").replace("mailto:", "")))
        );
    }

}
