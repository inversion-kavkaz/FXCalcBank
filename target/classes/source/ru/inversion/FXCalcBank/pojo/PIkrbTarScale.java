package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

    public PIkrbTarScale(){}

    @Id 
    @Column(name="ITARID",nullable = false,length = 0)
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
}