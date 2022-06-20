package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Komp
 * @since 2022/06/17 10:54:14
 */
@Entity(name = "ru.inversion.FXCalcBank.PIkrbTdDef")
@Table(name = "IKRB_TD_DEF")
public class PIkrbTdDef implements Serializable {
    private static final long serialVersionUID = 17_06_2022_10_54_14l;

    private Long IPLT_SHEMID;
    private Long IPLT_EVENT;
    private Long ITOPNUM;
    private String ITOPNAME;
    private Long ITOPNUMDEF;
    private Long IPLTDDEFID;
    private String CTOPCUR;
    private Long IPLT_PRIOR;

    public PIkrbTdDef() {
    }

    @Column(name = "IPLT_SHEMID", length = 3)
    public Long getIPLT_SHEMID() {
        return IPLT_SHEMID;
    }

    public void setIPLT_SHEMID(Long val) {
        IPLT_SHEMID = val;
    }

    @Column(name = "IPLT_EVENT", nullable = false, length = 5)
    public Long getIPLT_EVENT() {
        return IPLT_EVENT;
    }

    public void setIPLT_EVENT(Long val) {
        IPLT_EVENT = val;
    }

    @Column(name = "ITOPNUM", nullable = false, length = 3)
    public Long getITOPNUM() {
        return ITOPNUM;
    }

    public void setITOPNUM(Long val) {
        ITOPNUM = val;
    }

    @Column(name = "ITOPNUMDEF", length = 1)
    public Long getITOPNUMDEF() {
        return ITOPNUMDEF;
    }

    public void setITOPNUMDEF(Long val) {
        ITOPNUMDEF = val;
    }

    @Id
    @DBReturningValue
    @Column(name = "IPLTDDEFID")
    public Long getIPLTDDEFID() {
        return IPLTDDEFID;
    }

    public void setIPLTDDEFID(Long val) {
        IPLTDDEFID = val;
    }

    @Column(name = "CTOPCUR", length = 3)
    public String getCTOPCUR() {
        return CTOPCUR;
    }

    public void setCTOPCUR(String val) {
        CTOPCUR = val;
    }

    @Column(name = "IPLT_PRIOR", length = 3)
    public Long getIPLT_PRIOR() {
        return IPLT_PRIOR;
    }
    public void setIPLT_PRIOR(Long val) {
        IPLT_PRIOR = val;
    }

    @Column(name = "ITOPNAME", length = 64, columnDefinition = "(select CTOPNAME from top where itopnum = IKRB_TD_DEF.ITOPNUM)", updatable = false, insertable = false)
    public String getITOPNAME() {return ITOPNAME;}
    public void setITOPNAME(String ITOPNAME) {this.ITOPNAME = ITOPNAME;}
}