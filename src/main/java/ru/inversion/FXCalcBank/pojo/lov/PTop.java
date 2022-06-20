package ru.inversion.FXCalcBank.pojo.lov;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  Komp
@since   2022/06/17 15:33:26
*/
@Entity (name="ru.inversion.FXCalcBank.PTop")
@Table (name="TOP")
public class PTop implements Serializable
{
    private static final long serialVersionUID = 17_06_2022_15_33_26l;

    private String CTOPNAME;
    private Long ITOPNUM;

    public PTop(){}

    @Column(name="CTOPNAME",length = 64)
    public String getCTOPNAME() {
        return CTOPNAME;
    }
    public void setCTOPNAME(String val) {
        CTOPNAME = val; 
    }
    @Id 
    @Column(name="ITOPNUM",nullable = false,length = 3)
    public Long getITOPNUM() {
        return ITOPNUM;
    }
    public void setITOPNUM(Long val) {
        ITOPNUM = val; 
    }
}