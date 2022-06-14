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
@since   2022/05/20 11:36:17
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbe")
//@Table (name="IKRBE")
@NamedNativeQuery(name = "query", query = "" +
        "SELECT a.IKRBESTATUS,a.DIKRBEDATE,a.IMSGAPPID,a.MIKRBESUM,a.IKRBEID,a.ITYPEEVEVID, b.IPACKID \n" +
        "from ikrbe a, ikrb_pack_rec b " +
        "where a.ikrbeid = b.ipleeventid"
)
public class PIkrbe implements Serializable
{
    private static final long serialVersionUID = 20_05_2022_11_36_17l;

    private Long IKRBEID;
    private Long IPACKID;
    private LocalDate DIKRBEDATE;
    private BigDecimal MIKRBESUM;
    private Long ITYPEEVEVID;
    private Long IKRBESTATUS;
    private Long IMSGAPPID;


    public PIkrbe(){}

    @Id 
    @Column(name="IKRBEID",nullable = false,length = 12)
    public Long getIKRBEID() {
        return IKRBEID;
    }
    public void setIKRBEID(Long val) {
        IKRBEID = val; 
    }
    @Column(name="DIKRBEDATE",nullable = false)
    public LocalDate getDIKRBEDATE() {
        return DIKRBEDATE;
    }
    public void setDIKRBEDATE(LocalDate val) {
        DIKRBEDATE = val; 
    }
    @Column(name="MIKRBESUM",nullable = false,length = 16)
    public BigDecimal getMIKRBESUM() {
        return MIKRBESUM;
    }
    public void setMIKRBESUM(BigDecimal val) {
        MIKRBESUM = val; 
    }
    @Column(name="ITYPEEVEVID",nullable = false,length = 5)
    public Long getITYPEEVEVID() {
        return ITYPEEVEVID;
    }
    public void setITYPEEVEVID(Long val) {
        ITYPEEVEVID = val; 
    }
    @Column(name="IKRBESTATUS",length = 2)
    public Long getIKRBESTATUS() {
        return IKRBESTATUS;
    }
    public void setIKRBESTATUS(Long val) {
        IKRBESTATUS = val; 
    }
    @Column(name="IMSGAPPID",length = 10)
    public Long getIMSGAPPID() {    return IMSGAPPID;}
    public void setIMSGAPPID(Long IMSGAPPID) {this.IMSGAPPID = IMSGAPPID;}
    @Column(name="IPACKID",length = 12, updatable = false, insertable = false)
    public Long getIPACKID() {return IPACKID;}
    public void setIPACKID(Long IPACKID) { this.IPACKID = IPACKID;}
}