package ru.inversion.FXCalcBank.pojo.lov;

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
@Entity (name="ru.inversion.FXCalcBank.pojo.lov.PIkrbOpLov")
@NamedNativeQuery(name = "query", query = "SELECT " +
                                            "qrslt .*  from " +
                                            "(select CPROCCODENAME, CPROCCODE,CPLPLATSYSCODE from PL_PROC_CODE " +
                                            "where " +
                                            "not exists (select pl_op_Cod.CPLOPCODE from pl_op_Cod,pl_op where pl_op_Cod.CPLOPCODE=PL_PROC_CODE.CPROCCODE and pl_op.iplopid=PL_OP_COD.iplopid and pl_op.iplprocid=pl_adm.get_proc) " +
                                            "and not exists (select crbopcode from ikrb_op where ikrb_op.crbopcode = PL_PROC_CODE.CPROCCODE) " +
                                            "and IPLPROCID = pl_adm.get_proc) qrslt " +
                                            "ORDER BY CPROCCODE")
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