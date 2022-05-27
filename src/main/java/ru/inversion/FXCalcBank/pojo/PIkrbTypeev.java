package ru.inversion.FXCalcBank.pojo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import ru.inversion.db.entity.DBReturningValue;

import javax.persistence.*;
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
    private String CTYPEEV_COD;

    public PIkrbTypeev(){}

    @Id
    @DBReturningValue
    @Column(name="ITYPEEVEVID",nullable = false,length = 4)
    public Long getITYPEEVEVID() {
        return ITYPEEVEVID;
    }
    public void setITYPEEVEVID(Long val) {
        ITYPEEVEVID = val; 
    }
    @Column(name="CTYPEEVNAME",length = 200)
    public String getCTYPEEVNAME() {
        return CTYPEEVNAME;
    }
    public void setCTYPEEVNAME(String val) {
        CTYPEEVNAME = val; 
    }
    @Column(name="ITYPEEVTYPE",length = 1)
    public Long getITYPEEVTYPE() {return ITYPEEVTYPE;}
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
    @Column(name="IRBNUM",length = 10)
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
    public String getCTYPEEV_COD() {
        return CTYPEEV_COD;
    }
    public void setCTYPEEV_COD(String val) {
        CTYPEEV_COD = val; 
    }
}