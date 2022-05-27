package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeacc;
import ru.inversion.bicomp.stringconverter.BundleStringConverter;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.combobox.JInvComboBoxSimple;

import java.util.ResourceBundle;

/**
 * @author XDWeloper
 * @since Wed Feb 09 10:16:38 MSK 2022
 */
public class EditIkrbTypeaccController extends JInvFXFormController<PIkrbTypeacc> {

//    @FXML JInvLongField ITYPE_ACC;
//    @FXML JInvTextField CTYPE_ACCNAME;
    @FXML
    JInvComboBoxSimple ITYPE_FLAG;

    @Override
    protected void init() throws Exception {
        super.init();
        ResourceBundle rb = ResourceBundle.getBundle("ru/inversion/FXCalcBank/controllers/res/СhooseComboITYPE_FLAG");
        ITYPE_FLAG.setToolTipText(getBundleString("IYTPE_FLAG.TOOL"));
        ITYPE_FLAG.setConverter(new BundleStringConverter(rb));
        ITYPE_FLAG.getItems().clear();
        ITYPE_FLAG.getItems().addAll(rb.keySet());
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        if(getFormMode().equals(FormModeEnum.VM_INS))
            ITYPE_FLAG.getSelectionModel().selectFirst();
        else
            ITYPE_FLAG.getSelectionModel().select(getDataObject().getITYPE_FLAG());
    }
}

