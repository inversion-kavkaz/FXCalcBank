package ru.inversion.FXCalcBank;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  XDWeloper
@since   2022/02/28 17:56:33
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbTmpFile")
@Table (name="IKRB_TMP_FILE")
public class PIkrbTmpFile implements Serializable
{
    private static final long serialVersionUID = 28_02_2022_17_56_33l;

    private String FNAME;

    public PIkrbTmpFile(){}

    @Id 
    @Column(name="FNAME",length = 1000)
    public String getFNAME() {
        return FNAME;
    }
    public void setFNAME(String val) {
        FNAME = val; 
    }
}