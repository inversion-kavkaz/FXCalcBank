package ru.inversion.FXCalcBank.pojo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  Komp
@since   2022/05/18 17:28:58
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PPlPlt")
//@Table (name="PL_PLT")
@NamedNativeQuery(name = "query", query = "select * from pl_plt where csessionid = ikrb_main.get_SessID and nvl(mpltsumd,0) > 0 and nvl(ipltidparent,0) = 0")
public class PPlPlt implements Serializable
{
    private static final long serialVersionUID = 18_05_2022_17_28_58l;


    private Long IPLTID;
    private LocalDate DPLTCREATE;
    private LocalDate DPLTDOC;
    private LocalDate DPLTVAL;
    private String CPLTACCD;
    private String CPLTACCC;
    private BigDecimal MPLTEVTSUM;
    private BigDecimal MPLTSUMD;
    private String CPLTNAME;

    public PPlPlt(){}

    @Id 
    @Column(name="IPLTID",nullable = false,length = 12)
    public Long getIPLTID() {
        return IPLTID;
    }
    public void setIPLTID(Long val) {
        IPLTID = val; 
    }
    @Column(name="DPLTCREATE")
    public LocalDate getDPLTCREATE() {
        return DPLTCREATE;
    }
    public void setDPLTCREATE(LocalDate val) {
        DPLTCREATE = val; 
    }
    @Column(name="DPLTDOC")
    public LocalDate getDPLTDOC() {
        return DPLTDOC;
    }
    public void setDPLTDOC(LocalDate val) {
        DPLTDOC = val; 
    }
    @Column(name="DPLTVAL")
    public LocalDate getDPLTVAL() {
        return DPLTVAL;
    }
    public void setDPLTVAL(LocalDate val) {
        DPLTVAL = val; 
    }
    @Column(name="CPLTACCD",length = 25)
    public String getCPLTACCD() {
        return CPLTACCD;
    }
    public void setCPLTACCD(String val) {
        CPLTACCD = val; 
    }
    @Column(name="CPLTACCC",length = 25)
    public String getCPLTACCC() {
        return CPLTACCC;
    }
    public void setCPLTACCC(String val) {
        CPLTACCC = val; 
    }
    @Column(name="MPLTEVTSUM",length = 16)
    public BigDecimal getMPLTEVTSUM() {
        return MPLTEVTSUM;
    }
    public void setMPLTEVTSUM(BigDecimal val) {MPLTEVTSUM = val;}

    @Column(name="MPLTSUMD",length = 16)
    public BigDecimal getMPLTSUMD() {
        return MPLTSUMD;
    }
    public void setMPLTSUMD(BigDecimal val) {MPLTSUMD = val;}

    @Column(name="CPLTNAME",length = 1024)
    public String getCPLTNAME() {
        return CPLTNAME;
    }
    public void setCPLTNAME(String val) {
        CPLTNAME = val; 
    }
}