package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XDWeloper
 * @since 2022/02/15 14:44:14
 */
@SuppressWarnings("ALL")
@Entity(name = "ru.inversion.calc_bank.PDgRef")
@Table(name = "DG_REF")
public class PDgRef implements Serializable {
    private static final long serialVersionUID = 15_02_2022_14_44_14l;

    private Long IREFERENCENUM;
    private String CREFERENCESHORT;

    public PDgRef() {
    }

    @Id
    @Column(name = "IREFERENCENUM", nullable = false, length = 5)
    public Long getIREFERENCENUM() {
        return IREFERENCENUM;
    }

    public void setIREFERENCENUM(Long val) {
        IREFERENCENUM = val;
    }

    @Column(name = "CREFERENCESHORT", length = 40)
    public String getCREFERENCESHORT() {
        return CREFERENCESHORT;
    }

    public void setCREFERENCESHORT(String val) {
        CREFERENCESHORT = val;
    }
}