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
@since   2022/06/17 15:34:17
*/
@Entity (name="ru.inversion.FXCalcBank.PVPlCur")
@Table (name="V_PL_CUR")
public class PVPlCur implements Serializable
{
    private static final long serialVersionUID = 17_06_2022_15_34_17l;

    private String CCURISO;
    private String CCURCODE;

    public PVPlCur(){}

    @Column(name="CCURISO",length = 3)
    public String getCCURISO() {
        return CCURISO;
    }
    public void setCCURISO(String val) {
        CCURISO = val; 
    }
    @Id 
    @Column(name="CCURCODE",length = 3)
    public String getCCURCODE() {
        return CCURCODE;
    }
    public void setCCURCODE(String val) {
        CCURCODE = val; 
    }
}