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
    @DBReturningValue
    @Column(name="IBANK_ACCID",length = 3)
    public Long getIBANK_ACCID() {
        return IBANK_ACCID;
    }
    public void setIBANK_ACCID(Long val) {
        IBANK_ACCID = val; 
    }
    @Column(name="IBANKID",length = 10)
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