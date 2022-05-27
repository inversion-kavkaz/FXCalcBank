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
@since   2022/05/27 12:55:34
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbGrbin")
@Table (name="IKRB_GRBIN")
public class PIkrbGrbin implements Serializable
{
    private static final long serialVersionUID = 27_05_2022_12_55_34l;


/*
* Уникальный ID группы
*/
    private Long IGRBINID;

/*
* Наименование группы
*/
    private String CGRBINNAME;

    public PIkrbGrbin(){}

    @Id 
    @Column(name="IGRBINID",nullable = false,length = 12)
    public Long getIGRBINID() {
        return IGRBINID;
    }
    public void setIGRBINID(Long val) {
        IGRBINID = val; 
    }
    @Column(name="CGRBINNAME",length = 200)
    public String getCGRBINNAME() {
        return CGRBINNAME;
    }
    public void setCGRBINNAME(String val) {
        CGRBINNAME = val; 
    }
}