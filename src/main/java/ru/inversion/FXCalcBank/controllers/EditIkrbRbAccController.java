package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbRbAcc;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeacc;
import ru.inversion.FXCalcBank.pojo.lov.PIkrbAccNameLov;
import ru.inversion.dataset.DataSetException;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author XDWeloper
 * @since Wed Feb 09 10:19:55 MSK 2022
 */
public class EditIkrbRbAccController extends JInvFXFormController<PIkrbRbAcc> {
//
//
//
//    @FXML JInvLongField IRB_ACCID;
//    @FXML JInvLongField IRBNUM;
//    @FXML JInvTextField CRBACC;
//    @FXML JInvTextField CRBCUR;
    @FXML JInvTextField TYPE_ACC_NAME;
    @FXML JInvLongField ITYPE_ACC;

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        super.init();
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        JInvEntityLov itype_acc = setLov(
                this,
                null,
                PIkrbTypeacc.class,
                "CTYPE_ACCNAME",
                "ITYPE_FLAG = 1",
                "",
                TYPE_ACC_NAME,
                true,
                true,
                null
        );
        itype_acc.bindControl(ITYPE_ACC,param -> ((PIkrbTypeacc)param).getITYPE_ACC());

        if(!getFormMode().equals(FormModeEnum.VM_INS)){
            Long iTypeAcc = getDataObject().getITYPE_ACC();
            try {
                final String ctype_accname1 = populateDataSet(PIkrbTypeacc.class, null, "ITYPE_ACC = " + iTypeAcc, null, 1).getRow(0).getCTYPE_ACCNAME();
                TYPE_ACC_NAME.setText(ctype_accname1);
            } catch (DataSetException e) {
                e.printStackTrace();
            }
        }


    }
}

