package ru.inversion.FXCalcBank.pojo.lov;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
@author  Komp
@since   2022/06/18 14:27:00
*/
@Entity (name="ru.inversion.FXCalcBank.PDgRef")
@Table (name="DG_REF")
public class PDgRef implements Serializable
{
    private static final long serialVersionUID = 18_06_2022_14_27_00l;


    private String CREFERENCENAME;
    private String CREFERENCESHORT;
    private Long IREFERENCENUM;

    public PDgRef(){}

    @Column(name="CREFERENCENAME",nullable = false,length = 1000)
    public String getCREFERENCENAME() {
        return CREFERENCENAME;
    }
    public void setCREFERENCENAME(String val) {
        CREFERENCENAME = val; 
    }
    @Column(name="CREFERENCESHORT",length = 40)
    public String getCREFERENCESHORT() {
        return CREFERENCESHORT;
    }
    public void setCREFERENCESHORT(String val) {
        CREFERENCESHORT = val; 
    }
    @Id 
    @Column(name="IREFERENCENUM",nullable = false,length = 5)
    public Long getIREFERENCENUM() {
        return IREFERENCENUM;
    }
    public void setIREFERENCENUM(Long val) {
        IREFERENCENUM = val; 
    }
}