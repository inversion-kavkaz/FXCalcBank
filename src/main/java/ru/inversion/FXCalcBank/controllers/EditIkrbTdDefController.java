package ru.inversion.FXCalcBank.controllers;


import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTdDef;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeacc;
import ru.inversion.FXCalcBank.pojo.lov.PTop;
import ru.inversion.FXCalcBank.pojo.lov.PVPlCur;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author  Komp
 * @since   Fri Jun 17 10:54:24 MSK 2022
 */
public class EditIkrbTdDefController extends JInvFXFormController <PIkrbTdDef>
{  
//
//
//
//    @FXML JInvLongField IPLT_SHEMID;
    @FXML JInvLongField IPLT_EVENT;
    @FXML JInvLongField ITOPNUM;
    @FXML JInvTextField ITOPNAME;

//    @FXML JInvLongField ITOPNUMDEF;
//    @FXML JInvLongField IPLTDDEFID;
    @FXML JInvTextField CTOPCUR;
//    @FXML JInvLongField IPLT_PRIOR;



    @Override
    protected void init () throws Exception 
    {
        super.init ();
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        final JInvEntityLov topNameLov = setLov(
                this,
                "Выбрать!",
                PTop.class,
                "CTOPNAME",
                "itopnum in (1) ",
                "itopnum",
                ITOPNAME,
                true,
                true,
                null
        );
        topNameLov.bindControl(ITOPNUM,param -> ((PTop)param).getITOPNUM());

        final JInvEntityLov curLov = setLov(
                this,
                "Выбрать!",
                PVPlCur.class,
                "CCURISO",
                null,
                "CCURISO",
                CTOPCUR,
                true,
                true,
                null
        );
    }
}

