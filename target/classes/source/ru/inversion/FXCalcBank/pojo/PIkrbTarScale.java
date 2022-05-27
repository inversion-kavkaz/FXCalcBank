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
@since   2022/02/10 15:32:55
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbTarScale")
@Table (name="IKRB_TAR_SCALE")
public class PIkrbTarScale implements Serializable
{
    private static final long serialVersionUID = 10_02_2022_15_32_55l;

    private Long ITARID;
    private String CTARNAME;
    private Long ITARFIX;
    private Long ITARPERCENT;
    private Long ITARMIN;
    private Long ITARMAX;
    private Long ITARCALC;
    private String CTAR_COD;
    private String ITARCALC_STRING;


    public PIkrbTarScale(){}

    @Id
    @DBReturningValue
    @Column(name="ITARID",length = 0)
    public Long getITARID() {
        return ITARID;
    }
    public void setITARID(Long val) {
        ITARID = val; 
    }
    @Column(name="CTARNAME",nullable = false,length = 200)
    public String getCTARNAME() {
        return CTARNAME;
    }
    public void setCTARNAME(String val) {
        CTARNAME = val; 
    }
    @Column(name="ITARFIX",length = 0)
    public Long getITARFIX() {
        return ITARFIX;
    }
    public void setITARFIX(Long val) {
        ITARFIX = val; 
    }
    @Column(name="ITARPERCENT",length = 0)
    public Long getITARPERCENT() {
        return ITARPERCENT;
    }
    public void setITARPERCENT(Long val) {
        ITARPERCENT = val; 
    }
    @Column(name="ITARMIN",length = 0)
    public Long getITARMIN() {
        return ITARMIN;
    }
    public void setITARMIN(Long val) {
        ITARMIN = val; 
    }
    @Column(name="ITARMAX",length = 0)
    public Long getITARMAX() {
        return ITARMAX;
    }
    public void setITARMAX(Long val) {
        ITARMAX = val; 
    }
    @Column(name="ITARCALC",length = 0)
    public Long getITARCALC() {return ITARCALC;}
    public void setITARCALC(Long ITARCALC) {
        this.ITARCALC = ITARCALC;
        this.ITARCALC_STRING = ITARCALC.equals(1L) ? "Да" : "Нет";
    }
    @Column(name="CTAR_COD",length = 4000)
    public String getCTAR_COD() {return CTAR_COD;}
    public void setCTAR_COD(String CTAR_COD) {this.CTAR_COD = CTAR_COD;}

    @Transient
    public String getITARCALC_STRING() {return ITARCALC_STRING;}
    public void setITARCALC_STRING(String ITARCALC_STRING) {this.ITARCALC_STRING = ITARCALC_STRING;}
}