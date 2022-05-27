package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XDWeloper
 * @since 2022/02/10 16:03:19
 */
@Entity(name = "ru.inversion.FXCalcBank.PIkrbRb")
@Table(name = "IKRB_RB")
public class PIkrbRb implements Serializable {
    private static final long serialVersionUID = 10_02_2022_16_03_19l;

    private Long IRBNUM;
    private String CRBNAME;
    private Long IRBSHEMCOR;
    private Long IRBPROCTYPE;
    private String IDSMR;
    private Long IRBEXTENDID;
    private String CUSRLOGNAME;
    private String CBIC;

    public PIkrbRb() {
    }

    @Id
    @DBReturningValue
    @Column(name = "IRBNUM", length = 10)
    public Long getIRBNUM() {
        return IRBNUM;
    }

    public void setIRBNUM(Long val) {
        IRBNUM = val;
    }

    @Column(name = "CRBNAME", nullable = false, length = 60)
    public String getCRBNAME() {
        return CRBNAME;
    }

    public void setCRBNAME(String val) {
        CRBNAME = val;
    }

    @Column(name = "IRBSHEMCOR", length = 10)
    public Long getIRBSHEMCOR() {
        return IRBSHEMCOR;
    }

    public void setIRBSHEMCOR(Long val) {
        IRBSHEMCOR = val;
    }

    @Column(name = "IRBPROCTYPE", length = 2)
    public Long getIRBPROCTYPE() {
        return IRBPROCTYPE;
    }

    public void setIRBPROCTYPE(Long val) {
        IRBPROCTYPE = val;
    }

    @Column(name = "IDSMR", length = 3)
    public String getIDSMR() {
        return IDSMR;
    }

    public void setIDSMR(String val) {
        IDSMR = val;
    }

    @Column(name = "IRBEXTENDID", length = 10)
    public Long getIRBEXTENDID() {
        return IRBEXTENDID;
    }

    public void setIRBEXTENDID(Long val) {
        IRBEXTENDID = val;
    }

    @Column(name = "CUSRLOGNAME", length = 50)
    public String getCUSRLOGNAME() {
        return CUSRLOGNAME;
    }

    public void setCUSRLOGNAME(String val) {
        CUSRLOGNAME = val;
    }

    @Column(name = "CBIC", length = 10)
    public String getCBIC() {
        return CBIC;
    }

    public void setCBIC(String val) {
        CBIC = val;
    }
}