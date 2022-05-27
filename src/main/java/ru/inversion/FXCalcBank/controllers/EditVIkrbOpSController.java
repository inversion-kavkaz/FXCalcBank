package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTarScale;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
import ru.inversion.FXCalcBank.pojo.PVIkrbOpS;
import ru.inversion.FXCalcBank.pojo.lov.PIkrbAccNameLov;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author XDWeloper
 * @since Tue Feb 22 16:51:21 MSK 2022
 */
public class EditVIkrbOpSController extends JInvFXFormController<PVIkrbOpS> {
//
//
//
//    @FXML JInvLongField IRBOPSID;
//    @FXML JInvLongField IRBOPSPRIOR;
//    @FXML JInvTextField CRBOPSOPER;
//    @FXML JInvTextField DRBOPSDATE;
//    @FXML JInvLongField IRBOPID;
//    @FXML JInvTextField CRBOPNAME;
    @FXML JInvLongField ITYPEEVEVID;
    @FXML JInvTextField CTYPEEVNAME;
//    @FXML JInvLongField IBANKID;
//    @FXML JInvTextField CBANKNAME;
    @FXML JInvLongField ITARID;
    @FXML JInvTextField CTARNAME;

    @Override
    protected void init() throws Exception {
        super.init();
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        final JInvEntityLov ctypeevname = setLov(
                this,
                null,
                PIkrbTypeev.class,
                "CTYPEEVNAME",
                "",
                "",
                CTYPEEVNAME,
                true,
                true,
                null
        );
        ctypeevname.bindControl(ITYPEEVEVID,param ->  ((PIkrbTypeev)param).getITYPEEVEVID() );

        final JInvEntityLov ctarname = setLov(
                this,
                null,
                PIkrbTarScale.class,
                "CTARNAME",
                "",
                "",
                CTARNAME,
                false,
                true,
                null
        );
        ctarname.bindControl(ITARID,param -> ((PIkrbTarScale)param).getITARID());
    }

}

