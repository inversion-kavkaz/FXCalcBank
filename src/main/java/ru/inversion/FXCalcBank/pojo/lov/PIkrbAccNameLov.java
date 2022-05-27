package ru.inversion.FXCalcBank.pojo.lov;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author XDWeloper
 * @since 2022/02/22 19:43:33
 */
@Entity(name = "ru.inversion.FXCalcBank.pojo.lov.PIkrbAccNameLov")
@NamedNativeQuery(name = "query", query = "select itype_acc, ctype_accname FROM IKRB_TYPEACC")
public class PIkrbAccNameLov implements Serializable {
    private static final long serialVersionUID = 24_02_2022_17_04_37l;

    private Long ITYPE_ACC;
    private String CTYPE_ACCNAME;

    public PIkrbAccNameLov(){}

    @Id
    @Column(name="ITYPE_ACC",nullable = false,length = 5)
    public Long getITYPE_ACC() {
        return ITYPE_ACC;
    }
    public void setITYPE_ACC(Long val) {
        ITYPE_ACC = val;
    }
    @Column(name="CTYPE_ACCNAME",nullable = false,length = 150)
    public String getCTYPE_ACCNAME() {
        return CTYPE_ACCNAME;
    }
    public void setCTYPE_ACCNAME(String val) {
        CTYPE_ACCNAME = val;
    }
}

