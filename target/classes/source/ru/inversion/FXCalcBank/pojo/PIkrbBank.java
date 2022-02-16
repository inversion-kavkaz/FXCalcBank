package ru.inversion.FXCalcBank.pojo;

import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
@author  XDWeloper
@since   2022/02/15 10:44:54
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbBank")
@Table (name="IKRB_BANK")
public class PIkrbBank implements Serializable
{
    private static final long serialVersionUID = 15_02_2022_10_44_54l;

    private Long IBANKID;
    private String CBANKNAME;
    private String CBANKUSER;
    private Long IBANKSTATUS;
    private LocalDate DBANKOPEN;
    private LocalDate DBANKCLOSED;
    private String CBIC;
    private Long IPLPEXTENDID;
    private Long IRBNUM;
    private String FICODE;

    public PIkrbBank(){}

    @Id
    @DBReturningValue
    @Column(name="IBANKID",length = 10)
    public Long getIBANKID() {
        return IBANKID;
    }
    public void setIBANKID(Long val) {
        IBANKID = val; 
    }
    @Column(name="CBANKNAME",length = 200)
    public String getCBANKNAME() {
        return CBANKNAME;
    }
    public void setCBANKNAME(String val) {
        CBANKNAME = val; 
    }
    @Column(name="CBANKUSER",length = 50)
    public String getCBANKUSER() {
        return CBANKUSER;
    }
    public void setCBANKUSER(String val) {
        CBANKUSER = val; 
    }
    @Column(name="IBANKSTATUS",length = 2)
    public Long getIBANKSTATUS() {
        return IBANKSTATUS;
    }
    public void setIBANKSTATUS(Long val) {
        IBANKSTATUS = val; 
    }
    @Column(name="DBANKOPEN")
    public LocalDate getDBANKOPEN() {
        return DBANKOPEN;
    }
    public void setDBANKOPEN(LocalDate val) {
        DBANKOPEN = val; 
    }
    @Column(name="DBANKCLOSED")
    public LocalDate getDBANKCLOSED() {
        return DBANKCLOSED;
    }
    public void setDBANKCLOSED(LocalDate val) {
        DBANKCLOSED = val; 
    }
    @Column(name="CBIC",length = 10)
    public String getCBIC() {
        return CBIC;
    }
    public void setCBIC(String val) {
        CBIC = val; 
    }
    @Column(name="IPLPEXTENDID",length = 10)
    public Long getIPLPEXTENDID() {
        return IPLPEXTENDID;
    }
    public void setIPLPEXTENDID(Long val) {
        IPLPEXTENDID = val; 
    }
    @Column(name="IRBNUM",length = 10)
    public Long getIRBNUM() {
        return IRBNUM;
    }
    public void setIRBNUM(Long val) {
        IRBNUM = val; 
    }
    @Column(name="FICODE",length = 15)
    public String getFICODE() {
        return FICODE;
    }
    public void setFICODE(String val) {
        FICODE = val; 
    }
}