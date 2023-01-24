package fi.vm.sade.eperusteet.pdf.domain;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "dokumentti")
@Getter
@Setter
public class Dokumentti implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    private Long sisaltoId;

    @NotNull
    private String tyyppi;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DokumenttiTila tila = DokumenttiTila.EI_OLE;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Kieli kieli;

    @NotNull
    private Integer revision;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date aloitusaika;

    @Temporal(TemporalType.TIMESTAMP)
    private Date valmistumisaika;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "dokumenttidata")
    private byte[] data;
}
