package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbOp;
import ru.inversion.FXCalcBank.pojo.lov.PIkrbOpLov;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author XDWeloper
 * @since Tue Feb 22 16:50:42 MSK 2022
 */
public class EditIkrbOpController extends JInvFXFormController<PIkrbOp> {
//
//
//
//    @FXML JInvLongField IRBOPID;
    @FXML JInvTextField CRBOPNAME;
//    @FXML JInvTextField CRBOPOPER;
//    @FXML JInvTextField DRBOPDATE;
//    @FXML JInvLongField IRBNUM;
    @FXML JInvTextField CRBOPCODE;

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        super.init();

        setLov(
            this,
            null,
            PIkrbOpLov.class,
            "CPROCCODE",
            "",
            "",
            CRBOPCODE,
            true,
            true,
            null
        );
    }

}

