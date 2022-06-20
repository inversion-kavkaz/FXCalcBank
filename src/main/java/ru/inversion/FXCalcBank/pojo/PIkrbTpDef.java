package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Clob;

/**
 * @author Komp
 * @since 2022/06/17 10:55:54
 */
@Entity(name = "ru.inversion.FXCalcBank.PIkrbTpDef")
@Table(name = "IKRB_TP_DEF")
public class PIkrbTpDef implements Serializable {
    private static final long serialVersionUID = 17_06_2022_10_55_54l;

    private Long IPLTDDEFID;
    private Long IPLTPDEFID;
    private String CPLTPVAL;
    private String IPLTPTYPENAME;
    private Long IPLTPTYPE;
    private Long IPLTPCALC;
    private String CPLTPCOD;
    private Long IPLREFID;

    public PIkrbTpDef() {
    }

    @Column(name = "IPLTDDEFID", length = 5)
    public Long getIPLTDDEFID() {
        return IPLTDDEFID;
    }
    public void setIPLTDDEFID(Long val) {
        IPLTDDEFID = val;
    }

    @Id
    @DBReturningValue
    @Column(name = "IPLTPDEFID",  length = 7)
    public Long getIPLTPDEFID() {
        return IPLTPDEFID;
    }

    public void setIPLTPDEFID(Long val) {
        IPLTPDEFID = val;
    }

    @Column(name = "CPLTPVAL", length = 300)
    public String getCPLTPVAL() {
        return CPLTPVAL;
    }

    public void setCPLTPVAL(String val) {
        CPLTPVAL = val;
    }

    @Column(name = "IPLTPTYPE", length = 3)
    public Long getIPLTPTYPE() {
        return IPLTPTYPE;
    }

    public void setIPLTPTYPE(Long val) {
        IPLTPTYPE = val;
    }

    @Column(name = "IPLTPCALC", length = 1)
    public Long getIPLTPCALC() {
        return IPLTPCALC;
    }

    public void setIPLTPCALC(Long val) {
        IPLTPCALC = val;
    }

    @Column(name = "CPLTPCOD")
    public String getCPLTPCOD() {
        return CPLTPCOD;
    }

    public void setCPLTPCOD(String val) {
        CPLTPCOD = val;
    }

    @Column(name = "IPLREFID", length = 5)
    public Long getIPLREFID() {
        return IPLREFID;
    }

    public void setIPLREFID(Long val) {
        IPLREFID = val;
    }

    @Column(name = "IPLTPTYPENAME", length = 250, columnDefinition = "(SELECT CREFERENCENAME FROM DG_REF WHERE creferencetype ='PL_TP' AND IREFERENCENUM = IPLTPTYPE)",
    updatable = false, insertable = false)
    public String getIPLTPTYPENAME() {return IPLTPTYPENAME;}
    public void setIPLTPTYPENAME(String IPLTPTYPENAME) {this.IPLTPTYPENAME = IPLTPTYPENAME;}
}