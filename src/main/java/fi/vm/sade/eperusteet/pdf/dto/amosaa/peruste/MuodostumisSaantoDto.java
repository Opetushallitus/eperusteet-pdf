package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.LaajuusYksikko;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MuodostumisSaantoDto {

    private Laajuus laajuus;
    private Koko koko;

    public MuodostumisSaantoDto() {
    }

    public MuodostumisSaantoDto(Laajuus laajuus) {
        this.laajuus = laajuus;
        this.koko = null;
    }

    public MuodostumisSaantoDto(Koko koko) {
        this.koko = koko;
        this.laajuus = null;
    }

    public MuodostumisSaantoDto(Laajuus laajuus, Koko koko) {
        this.koko = koko;
        this.laajuus = laajuus;
    }

    @Getter
    @Setter
    public static class Laajuus {

        private Integer minimi;
        private Integer maksimi;
        private LaajuusYksikko yksikko;

        public Laajuus(Integer minimi, Integer maksimi, LaajuusYksikko yksikko) {
            this.minimi = minimi;
            this.maksimi = maksimi;
            this.yksikko = yksikko;
        }

        public Laajuus() {
        }

    }

    @Getter
    @Setter
    public static class Koko {

        Integer minimi;
        Integer maksimi;

        public Koko(Integer minimi, Integer maksimi) {
            this.minimi = minimi;
            this.maksimi = maksimi;
        }

        public Koko() {

        }
    }
}
