package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.domain.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.domain.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas.lops2019.Lops2019SisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.TutkintoonvalmentavaSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.VapaasivistystyoSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOpetuksenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.EsiopetuksenPerusteenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.PerusopetuksenPerusteenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TPOOpetuksenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.julkinen.LukiokoulutuksenPerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerusteKaikkiDto extends PerusteBaseDto {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<SuoritustapaLaajaDto> suoritustavat;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<TutkinnonOsaKaikkiDto> tutkinnonOsat;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<KoulutuksenOsaDto> koulutuksenOsat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("perusopetus")
    private PerusopetuksenPerusteenSisaltoDto perusopetuksenPerusteenSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("lukiokoulutus")
    private LukiokoulutuksenPerusteenSisaltoDto lukiokoulutuksenPerusteenSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("lops2019")
    private Lops2019SisaltoDto lops2019Sisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("esiopetus")
    private EsiopetuksenPerusteenSisaltoDto esiopetuksenPerusteenSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("aipe")
    private AIPEOpetuksenSisaltoDto aipeOpetuksenPerusteenSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tpo")
    private TPOOpetuksenSisaltoDto tpoOpetuksenSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("vapaasivistystyo")
    private VapaasivistystyoSisaltoDto vstSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tutkintoonvalmentava")
    private TutkintoonvalmentavaSisaltoDto tuvasisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("opas")
    private OpasSisaltoDto oppaanSisalto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("digitaalinenosaaminen")
    private DigitaalisenOsaamisenSisaltoDto digitaalisenOsaamisenSisalto;

    @JsonIgnore
    public Set<PerusteenSisaltoDto> getSisallot() {
        if (PerusteTyyppi.OPAS.equals(this.getTyyppi())) {
            return Collections.singleton(this.getOppaanSisalto());
        } else {
            if (KoulutustyyppiToteutus.AMMATILLINEN.equals(this.getToteutus())) {
                return new HashSet<>(this.getSuoritustavat());
            } else if (this.getPerusopetuksenPerusteenSisalto() != null) {
                return Collections.singleton(this.getPerusopetuksenPerusteenSisalto());
            } else if (this.getLops2019Sisalto() != null) {
                return Collections.singleton(this.getLops2019Sisalto());
            } else if (this.getEsiopetuksenPerusteenSisalto() != null) {
                return Collections.singleton(this.getEsiopetuksenPerusteenSisalto());
            } else if (this.getLukiokoulutuksenPerusteenSisalto() != null) {
                return Collections.singleton(this.getLukiokoulutuksenPerusteenSisalto());
            } else if (this.getAipeOpetuksenPerusteenSisalto() != null) {
                return Collections.singleton(this.getAipeOpetuksenPerusteenSisalto());
            } else if (this.getTpoOpetuksenSisalto() != null) {
                return Collections.singleton(this.getTpoOpetuksenSisalto());
            } else if (this.getVstSisalto() != null) {
                return Collections.singleton(this.getVstSisalto());
            } else if (this.getTuvasisalto() != null) {
                return Collections.singleton(this.getTuvasisalto());
            } else if (this.getDigitaalisenOsaamisenSisalto() != null) {
                return Collections.singleton(this.getDigitaalisenOsaamisenSisalto());
            }
        }
        return new HashSet<>();
    }
}
