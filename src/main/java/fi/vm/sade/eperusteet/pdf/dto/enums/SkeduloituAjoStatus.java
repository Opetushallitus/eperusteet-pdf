package fi.vm.sade.eperusteet.pdf.dto.enums;

public enum SkeduloituAjoStatus {

    AJOSSA("ajossa"),
    PYSAYTETTY("pysaytetty"),
    AJOVIRHE("ajovirhe");

    private String status;

    SkeduloituAjoStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
