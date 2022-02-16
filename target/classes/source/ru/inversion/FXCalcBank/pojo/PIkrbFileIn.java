package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author XDWeloper
 * @since 2022/02/07 11:57:19
 */
@SuppressWarnings("ALL")
@Entity(name = "ru.inversion.FXCalcBank.pojo.PIkrbFileIn")
@Table(name = "IKRB_FILE_IN")
public class PIkrbFileIn implements Serializable {
    private static final long serialVersionUID = 7_02_2022_11_57_19l;

    private Long IFILEINID;
    private String CFILEINNAME;
    private Long IFILEINSTATUS;
    private LocalDate DFILEINLOAD;
    private Long IPLFID;
    private String CUSRLOGNAME;
    private LocalDate DFILEINDATE;
    private String IFILEIDPROC;
    private Long IFILECALC;

    public PIkrbFileIn() {
    }

    @Id
    @Column(name = "IFILEINID", nullable = false, length = 10)
    public Long getIFILEINID() {
        return IFILEINID;
    }

    public void setIFILEINID(Long val) {
        IFILEINID = val;
    }

    @Column(name = "CFILEINNAME", nullable = false, length = 255)
    public String getCFILEINNAME() {
        return CFILEINNAME;
    }

    public void setCFILEINNAME(String val) {
        CFILEINNAME = val;
    }

    @Column(name = "IFILEINSTATUS", nullable = false, length = 5)
    public Long getIFILEINSTATUS() {
        return IFILEINSTATUS;
    }

    public void setIFILEINSTATUS(Long val) {
        IFILEINSTATUS = val;
    }

    @Column(name = "DFILEINLOAD", nullable = false)
    public LocalDate getDFILEINLOAD() {
        return DFILEINLOAD;
    }

    public void setDFILEINLOAD(LocalDate val) {
        DFILEINLOAD = val;
    }

    @Column(name = "IPLFID", nullable = false, length = 3)
    public Long getIPLFID() {
        return IPLFID;
    }

    public void setIPLFID(Long val) {
        IPLFID = val;
    }

    @Column(name = "CUSRLOGNAME", nullable = false, length = 40)
    public String getCUSRLOGNAME() {
        return CUSRLOGNAME;
    }

    public void setCUSRLOGNAME(String val) {
        CUSRLOGNAME = val;
    }

    @Column(name = "DFILEINDATE")
    public LocalDate getDFILEINDATE() {
        return DFILEINDATE;
    }

    public void setDFILEINDATE(LocalDate val) {
        DFILEINDATE = val;
    }

    @Column(name = "IFILEIDPROC", length = 20)
    public String getIFILEIDPROC() {
        return IFILEIDPROC;
    }

    public void setIFILEIDPROC(String val) {
        IFILEIDPROC = val;
    }

    @Column(name = "IFILECALC", length = 1)
    public Long getIFILECALC() {
        return IFILECALC;
    }

    public void setIFILECALC(Long val) {
        IFILECALC = val;
    }
}