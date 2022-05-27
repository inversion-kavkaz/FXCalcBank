package ru.inversion.FXCalcBank.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XDWeloper
 * @since 2022/02/22 12:27:21
 */
@Entity(name = "ru.inversion.FXCalcBank.PIkrbSysparam")
@Table(name = "IKRB_SYSPARAM")
public class PIkrbSysparam implements Serializable {
    private static final long serialVersionUID = 22_02_2022_12_27_21l;

    private Long IRBSYSID;
    private String CRBSYSNAME;
    private String CRBSYSVALUE;

    public PIkrbSysparam() {
    }

    @Id
    @Column(name = "IRBSYSID", nullable = false, length = 2)
    public Long getIRBSYSID() {
        return IRBSYSID;
    }

    public void setIRBSYSID(Long val) {
        IRBSYSID = val;
    }

    @Column(name = "CRBSYSNAME", length = 50)
    public String getCRBSYSNAME() {
        return CRBSYSNAME;
    }

    public void setCRBSYSNAME(String val) {
        CRBSYSNAME = val;
    }

    @Column(name = "CRBSYSVALUE", length = 250)
    public String getCRBSYSVALUE() {
        return CRBSYSVALUE;
    }

    public void setCRBSYSVALUE(String val) {
        CRBSYSVALUE = val;
    }
}