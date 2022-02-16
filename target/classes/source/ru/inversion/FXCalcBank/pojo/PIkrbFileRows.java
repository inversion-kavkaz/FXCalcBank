package ru.inversion.FXCalcBank.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
@author  XDWeloper
@since   2022/02/07 12:39:32
*/
@Entity (name="ru.inversion.FXCalcBank.pojo.PIkrbFileRowsa")
@Table (name="IKRB_FILE_ROWS")
public class PIkrbFileRows implements Serializable
{
    private static final long serialVersionUID = 7_02_2022_12_39_32l;

    private Long IMSGFINID;
    private Long IFILEINID;
    private Long IMSGLINE;
    private String CRAWSTR;

    public PIkrbFileRows(){}

    @Column(name="IMSGFINID",nullable = false,length = 12)
    public Long getIMSGFINID() {
        return IMSGFINID;
    }
    public void setIMSGFINID(Long val) {
        IMSGFINID = val; 
    }
    @Id 
    @Column(name="IFILEINID",length = 10)
    public Long getIFILEINID() {
        return IFILEINID;
    }
    public void setIFILEINID(Long val) {
        IFILEINID = val; 
    }
    @Column(name="IMSGLINE",length = 7)
    public Long getIMSGLINE() {
        return IMSGLINE;
    }
    public void setIMSGLINE(Long val) {
        IMSGLINE = val; 
    }
    @Column(name="CRAWSTR",length = 4000)
    public String getCRAWSTR() {
        return CRAWSTR;
    }
    public void setCRAWSTR(String val) {
        CRAWSTR = val; 
    }
}