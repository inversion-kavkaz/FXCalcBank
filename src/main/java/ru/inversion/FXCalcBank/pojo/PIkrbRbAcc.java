package ru.inversion.FXCalcBank.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
@author  XDWeloper
@since   2022/02/09 10:19:45
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbRbAcc")
@Table (name="IKRB_RB_ACC")
public class PIkrbRbAcc implements Serializable
{
    private static final long serialVersionUID = 9_02_2022_10_19_45l;

    private Long IRB_ACCID;
    private Long IRBNUM;
    private String CRBACC;
    private String CRBCUR;
    private Long ITYPE_ACC;

    public PIkrbRbAcc(){}

    @Id 
    @Column(name="IRB_ACCID",nullable = false,length = 3)
    public Long getIRB_ACCID() {
        return IRB_ACCID;
    }
    public void setIRB_ACCID(Long val) {
        IRB_ACCID = val; 
    }
    @Id 
    @Column(name="IRBNUM",nullable = false,length = 10)
    public Long getIRBNUM() {
        return IRBNUM;
    }
    public void setIRBNUM(Long val) {
        IRBNUM = val; 
    }
    @Column(name="CRBACC",length = 20)
    public String getCRBACC() {
        return CRBACC;
    }
    public void setCRBACC(String val) {
        CRBACC = val; 
    }
    @Column(name="CRBCUR",length = 3)
    public String getCRBCUR() {
        return CRBCUR;
    }
    public void setCRBCUR(String val) {
        CRBCUR = val; 
    }
    @Column(name="ITYPE_ACC",length = 3)
    public Long getITYPE_ACC() {
        return ITYPE_ACC;
    }
    public void setITYPE_ACC(Long val) {
        ITYPE_ACC = val;
    }
}