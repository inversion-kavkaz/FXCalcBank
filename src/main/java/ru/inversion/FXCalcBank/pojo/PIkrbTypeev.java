package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Clob;

/**
@author  XDWeloper
@since   2022/02/10 16:05:34
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbTypeev")
@Table (name="IKRB_TYPEEV")
public class PIkrbTypeev implements Serializable
{
    private static final long serialVersionUID = 10_02_2022_16_05_34l;

    private Long ITYPEEVEVID;
    private String CTYPEEVNAME;
    private Long ITYPEEVTYPE;
    private Long IPLTEVGR;
    private Long IRBNUM;
    private Long ITYPEEV_ACCD;
    private Long ITYPEEV_ACCC;
    private Clob CTYPEEV_COD;

    public PIkrbTypeev(){}

    @Id 
    @Column(name="ITYPEEVEVID",nullable = false,length = 4)
    public Long getITYPEEVEVID() {
        return ITYPEEVEVID;
    }
    public void setITYPEEVEVID(Long val) {
        ITYPEEVEVID = val; 
    }
    @Id 
    @Column(name="CTYPEEVNAME",nullable = false,length = 200)
    public String getCTYPEEVNAME() {
        return CTYPEEVNAME;
    }
    public void setCTYPEEVNAME(String val) {
        CTYPEEVNAME = val; 
    }
    @Column(name="ITYPEEVTYPE",length = 1)
    public Long getITYPEEVTYPE() {
        return ITYPEEVTYPE;
    }
    public void setITYPEEVTYPE(Long val) {
        ITYPEEVTYPE = val; 
    }
    @Column(name="IPLTEVGR",length = 2)
    public Long getIPLTEVGR() {
        return IPLTEVGR;
    }
    public void setIPLTEVGR(Long val) {
        IPLTEVGR = val; 
    }
    @Id 
    @Column(name="IRBNUM",nullable = false,length = 10)
    public Long getIRBNUM() {
        return IRBNUM;
    }
    public void setIRBNUM(Long val) {
        IRBNUM = val; 
    }
    @Column(name="ITYPEEV_ACCD",length = 5)
    public Long getITYPEEV_ACCD() {
        return ITYPEEV_ACCD;
    }
    public void setITYPEEV_ACCD(Long val) {
        ITYPEEV_ACCD = val; 
    }
    @Column(name="ITYPEEV_ACCC",length = 5)
    public Long getITYPEEV_ACCC() {
        return ITYPEEV_ACCC;
    }
    public void setITYPEEV_ACCC(Long val) {
        ITYPEEV_ACCC = val; 
    }
    @Column(name="CTYPEEV_COD")
    public Clob getCTYPEEV_COD() {
        return CTYPEEV_COD;
    }
    public void setCTYPEEV_COD(Clob val) {
        CTYPEEV_COD = val; 
    }
}