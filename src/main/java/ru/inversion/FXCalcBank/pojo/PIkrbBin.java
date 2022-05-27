package ru.inversion.FXCalcBank.pojo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.DBReturningValue;
import ru.inversion.db.entity.ProxyFor;

/**
@author  XDWeloper
@since   2022/05/27 12:56:11
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbBin")
@Table (name="IKRB_BIN")
public class PIkrbBin implements Serializable
{
    private static final long serialVersionUID = 27_05_2022_12_56_11l;
    private Long IBINID;
    private Long IGRBINID;
    private String CBIN;

    public PIkrbBin(){}

    @DBReturningValue
    @Id 
    @Column(name="IBINID",length = 12)
    public Long getIBINID() {
        return IBINID;
    }
    public void setIBINID(Long val) {
        IBINID = val; 
    }
    @Column(name="IGRBINID",length = 12)
    public Long getIGRBINID() {
        return IGRBINID;
    }
    public void setIGRBINID(Long val) {
        IGRBINID = val; 
    }
    @Column(name="CBIN",length = 10)
    public String getCBIN() {
        return CBIN;
    }
    public void setCBIN(String val) {
        CBIN = val; 
    }
}