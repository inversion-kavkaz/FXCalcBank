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
@since   2022/05/20 11:34:09
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbPack")
@Table (name="IKRB_PACK")
public class PIkrbPack implements Serializable
{
    private static final long serialVersionUID = 20_05_2022_11_34_09l;

    private Long IPACKID;
    private Long IPACKNUM;
    private String CFILENAME;
    private BigDecimal MPACKSUM;
    private LocalDate DPACKDATETIME;
    private Long IPACKSTATUS;

    public PIkrbPack(){}

    @Id 
    @Column(name="IPACKID",nullable = false,length = 16)
    public Long getIPACKID() {
        return IPACKID;
    }
    public void setIPACKID(Long val) {
        IPACKID = val; 
    }
    @Column(name="IPACKNUM",length = 4)
    public Long getIPACKNUM() {
        return IPACKNUM;
    }
    public void setIPACKNUM(Long val) {
        IPACKNUM = val; 
    }
    @Column(name="CFILENAME",length = 150)
    public String getCFILENAME() {
        return CFILENAME;
    }
    public void setCFILENAME(String val) {
        CFILENAME = val; 
    }
    @Column(name="MPACKSUM",nullable = false,length = 16)
    public BigDecimal getMPACKSUM() {
        return MPACKSUM;
    }
    public void setMPACKSUM(BigDecimal val) {
        MPACKSUM = val; 
    }
    @Column(name="DPACKDATETIME")
    public LocalDate getDPACKDATETIME() {
        return DPACKDATETIME;
    }
    public void setDPACKDATETIME(LocalDate val) {
        DPACKDATETIME = val; 
    }
    @Column(name="IPACKSTATUS",nullable = false,length = 1)
    public Long getIPACKSTATUS() {
        return IPACKSTATUS;
    }
    public void setIPACKSTATUS(Long val) {
        IPACKSTATUS = val; 
    }
}