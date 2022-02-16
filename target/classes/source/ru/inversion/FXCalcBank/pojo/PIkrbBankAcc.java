package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
@author  XDWeloper
@since   2022/02/09 10:20:59
*/
@Entity (name="ru.inversion.FXCalcBank.PIkrbBankAcc")
@Table (name="IKRB_BANK_ACC")
public class PIkrbBankAcc implements Serializable
{
    private static final long serialVersionUID = 9_02_2022_10_20_59l;

    private Long IBANK_ACCID;
    private Long IBANKID;
    private String CRBACC;
    private String CRBCUR;
    private Long ITYPE_ACC;

    public PIkrbBankAcc(){}

    @Id 
    @Column(name="IBANK_ACCID",nullable = false,length = 3)
    public Long getIBANK_ACCID() {
        return IBANK_ACCID;
    }
    public void setIBANK_ACCID(Long val) {
        IBANK_ACCID = val; 
    }
    @Id 
    @Column(name="IBANKID",nullable = false,length = 10)
    public Long getIBANKID() {
        return IBANKID;
    }
    public void setIBANKID(Long val) {
        IBANKID = val; 
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