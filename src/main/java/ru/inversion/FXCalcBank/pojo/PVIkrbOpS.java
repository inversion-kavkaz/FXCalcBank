package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author XDWeloper
 * @since 2022/02/22 16:51:12
 */
@Entity(name = "ru.inversion.FXCalcBank.PVIkrbOpS")
@Table(name = "IKRB_OP_S")
@NamedNativeQuery(name = "query", query = "select irbopsid,irbopsprior,crbopsoper,drbopsdate,irbopid," +
        "(select crbopname from ikrb_op where irbopid = a.irbopid) CRBOPNAME,itypeevevid," +
        "(select ctypeevname from ikrb_typeev where itypeevevid = a.itypeevevid) CTYPEEVNAME," +
        "ibankid, " +
        "(select cbankname from ikrb_bank where ibankid = a.ibankid) CBANKNAME,itarid," +
        "(select ctarname from ikrb_tar_scale where itarid = a.itarid)  CTARNAME from ikrb_op_s a")
public class PVIkrbOpS implements Serializable {
    private static final long serialVersionUID = 22_02_2022_16_51_12l;

    private Long IRBOPSID;
    private Long IRBOPSPRIOR;
    private String CRBOPSOPER;
    private LocalDate DRBOPSDATE;
    private Long IRBOPID;
    private String CRBOPNAME; //**** (select crbopname from ikrb_op where irbopid = a.irbopid) ---
    private Long ITYPEEVEVID;
    private String CTYPEEVNAME; //**** (select ctypeevname from ikrb_typeev where itypeevevid = a.itypeevevid)
    private Long IBANKID;
    private String CBANKNAME; //**** (select cbankname from ikrb_bank where ibankid = a.ibankid) ----
    private Long ITARID;
    private String CTARNAME; //**** (select ctarname from ikrb_tar_scale where itarid = a.itarid)

    public PVIkrbOpS() {
    }

    @Id
    @DBReturningValue
    @Column(name = "IRBOPSID",  length = 6)
    public Long getIRBOPSID() {
        return IRBOPSID;
    }
    public void setIRBOPSID(Long val) {
        IRBOPSID = val;
    }

    @Column(name = "IRBOPSPRIOR", length = 3)
    public Long getIRBOPSPRIOR() {
        return IRBOPSPRIOR;
    }
    public void setIRBOPSPRIOR(Long val) {
        IRBOPSPRIOR = val;
    }

    @Column(name = "CRBOPSOPER", length = 40)
    public String getCRBOPSOPER() {
        return CRBOPSOPER;
    }
    public void setCRBOPSOPER(String val) {
        CRBOPSOPER = val;
    }

    @Column(name = "DRBOPSDATE")
    public LocalDate getDRBOPSDATE() {
        return DRBOPSDATE;
    }
    public void setDRBOPSDATE(LocalDate val) {
        DRBOPSDATE = val;
    }

    @Column(name = "IRBOPID", length = 4)
    public Long getIRBOPID() {
        return IRBOPID;
    }
    public void setIRBOPID(Long val) {
        IRBOPID = val;
    }

    @Column(name = "CRBOPNAME", length = 100, insertable = false, updatable = false)
    public String getCRBOPNAME() {
        return CRBOPNAME;
    }
    public void setCRBOPNAME(String val) {
        CRBOPNAME = val;
    }

    @Column(name = "ITYPEEVEVID", length = 4)
    public Long getITYPEEVEVID() {
        return ITYPEEVEVID;
    }
    public void setITYPEEVEVID(Long val) {
        ITYPEEVEVID = val;
    }

    @Column(name = "CTYPEEVNAME", length = 200, insertable = false, updatable = false)
    public String getCTYPEEVNAME() {
        return CTYPEEVNAME;
    }
    public void setCTYPEEVNAME(String val) {
        CTYPEEVNAME = val;
    }

    @Column(name = "IBANKID", length = 4)
    public Long getIBANKID() {
        return IBANKID;
    }
    public void setIBANKID(Long val) {
        IBANKID = val;
    }

    @Column(name = "CBANKNAME", length = 200, insertable = false, updatable = false)
    public String getCBANKNAME() {
        return CBANKNAME;
    }
    public void setCBANKNAME(String val) {
        CBANKNAME = val;
    }

    @Column(name = "ITARID", length = 5)
    public Long getITARID() {
        return ITARID;
    }
    public void setITARID(Long val) {
        ITARID = val;
    }

    @Column(name = "CTARNAME", length = 200, insertable = false, updatable = false)
    public String getCTARNAME() {
        return CTARNAME;
    }
    public void setCTARNAME(String val) {
        CTARNAME = val;
    }
}