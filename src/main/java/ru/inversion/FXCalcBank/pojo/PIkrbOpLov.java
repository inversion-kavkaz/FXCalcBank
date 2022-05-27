package ru.inversion.FXCalcBank.pojo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  XDWeloper
@since   2022/02/22 19:43:33
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbOpLov")
public class PIkrbOpLov implements Serializable
{
    private static final long serialVersionUID = 22_02_2022_19_43_33l;

    private String CPROCCODENAME;
    private String CPROCCODE;
    private String CPLPLATSYSCODE;

    public PIkrbOpLov(){}

    @Column(name="CPROCCODENAME",length = 100)
    public String getCPROCCODENAME() {
        return CPROCCODENAME;
    }
    public void setCPROCCODENAME(String val) {
        CPROCCODENAME = val; 
    }
    @Id 
    @Column(name="CPROCCODE",nullable = false,length = 25)
    public String getCPROCCODE() {
        return CPROCCODE;
    }
    public void setCPROCCODE(String val) {
        CPROCCODE = val; 
    }
    @Column(name="CPLPLATSYSCODE",length = 50)
    public String getCPLPLATSYSCODE() {
        return CPLPLATSYSCODE;
    }
    public void setCPLPLATSYSCODE(String val) {
        CPLPLATSYSCODE = val; 
    }
}