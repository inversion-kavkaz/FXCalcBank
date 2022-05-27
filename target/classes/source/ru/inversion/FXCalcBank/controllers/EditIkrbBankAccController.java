package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbBankAcc;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeacc;
import ru.inversion.bicomp.control.JInvTextFieldAccountNum;
import ru.inversion.dataset.DataSetException;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author XDWeloper
 * @since Wed Feb 09 10:21:08 MSK 2022
 */
public class EditIkrbBankAccController extends JInvFXFormController<PIkrbBankAcc> {
//
//
//
//    @FXML JInvLongField IBANK_ACCID;
//    @FXML JInvLongField IBANKID;
    @FXML JInvTextFieldAccountNum CRBACC;
    @FXML JInvTextField TYPE_ACC_NAME;
//    @FXML JInvTextField CRBCUR;
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
        final JInvEntityLov ctype_accname = setLov(
                this,
                null,
                PIkrbTypeacc.class,
                "CTYPE_ACCNAME",
                "ITYPE_FLAG = 2",
                "",
                TYPE_ACC_NAME,
                true,
                true,
                null
        );
        ctype_accname.bindControl(ITYPE_ACC,param -> ((PIkrbTypeacc)param).getITYPE_ACC());

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

