package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author XDWeloper
 * @since 2022/02/22 16:50:32
 */
@Entity(name = "ru.inversion.FXCalcBank.PIkrbOp")
@Table(name = "IKRB_OP")
public class PIkrbOp implements Serializable {
    private static final long serialVersionUID = 22_02_2022_16_50_32l;

    private Long IRBOPID;
    private String CRBOPNAME;
    private String CRBOPOPER;
    private LocalDate DRBOPDATE;
    private Long IRBNUM;
    private String CRBOPCODE;

    public PIkrbOp() {
    }

    @Id
    @DBReturningValue
    @Column(name = "IRBOPID", nullable = false, length = 4)
    public Long getIRBOPID() {
        return IRBOPID;
    }

    public void setIRBOPID(Long val) {
        IRBOPID = val;
    }

    @Column(name = "CRBOPNAME", length = 100)
    public String getCRBOPNAME() {
        return CRBOPNAME;
    }

    public void setCRBOPNAME(String val) {
        CRBOPNAME = val;
    }

    @Column(name = "CRBOPOPER", length = 40)
    public String getCRBOPOPER() {
        return CRBOPOPER;
    }

    public void setCRBOPOPER(String val) {
        CRBOPOPER = val;
    }

    @Column(name = "DRBOPDATE")
    public LocalDate getDRBOPDATE() {
        return DRBOPDATE;
    }

    public void setDRBOPDATE(LocalDate val) {
        DRBOPDATE = val;
    }

    @Column(name = "IRBNUM", length = 10)
    public Long getIRBNUM() {
        return IRBNUM;
    }

    public void setIRBNUM(Long val) {
        IRBNUM = val;
    }

    @Column(name = "CRBOPCODE", length = 25)
    public String getCRBOPCODE() {
        return CRBOPCODE;
    }

    public void setCRBOPCODE(String val) {
        CRBOPCODE = val;
    }
}