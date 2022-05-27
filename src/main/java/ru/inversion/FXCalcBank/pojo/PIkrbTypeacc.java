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
@since   2022/02/09 10:16:26
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbTypeacc")
@Table (name="IKRB_TYPEACC")
public class PIkrbTypeacc implements Serializable
{
    private static final long serialVersionUID = 9_02_2022_10_16_26l;

    private Long ITYPE_ACC;
    private String CTYPE_ACCNAME;
    private Long ITYPE_FLAG;

    public PIkrbTypeacc(){}

    @Id
    @DBReturningValue
    @Column(name="ITYPE_ACC",length = 5)
    public Long getITYPE_ACC() {
        return ITYPE_ACC;
    }
    public void setITYPE_ACC(Long val) {
        ITYPE_ACC = val; 
    }
    @Id 
    @Column(name="CTYPE_ACCNAME",nullable = false,length = 150)
    public String getCTYPE_ACCNAME() {
        return CTYPE_ACCNAME;
    }
    public void setCTYPE_ACCNAME(String val) {
        CTYPE_ACCNAME = val; 
    }
    @Column(name="ITYPE_FLAG",length = 2)
    public Long getITYPE_FLAG() {
        return ITYPE_FLAG;
    }
    public void setITYPE_FLAG(Long val) {
        ITYPE_FLAG = val; 
    }
}