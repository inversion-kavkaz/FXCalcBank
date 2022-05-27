package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTarScale;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvCheckBox;
import ru.inversion.fx.form.controls.JInvTextArea;
import ru.inversion.utils.U;

/**
 * @author XDWeloper
 * @since Thu Feb 10 15:33:05 MSK 2022
 */
public class EditIkrbTarScaleController extends JInvFXFormController<PIkrbTarScale> {
//
//
@FXML JInvCheckBox ITARCALC_COMBO;
@FXML JInvTextArea CTAR_COD;

//    @FXML JInvLongField ITARID;
//    @FXML JInvTextField CTARNAME;
//    @FXML JInvLongField ITARFIX;
//    @FXML JInvLongField ITARPERCENT;
//    @FXML JInvLongField ITARMIN;
//    @FXML JInvLongField ITARMAX;

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        super.init();
        ITARCALC_COMBO.setSelected(U.nvl(getDataObject().getITARCALC(),0).equals(1L));
        CTAR_COD.visibleProperty().bind(ITARCALC_COMBO.selectedProperty());
    }

    @Override
    protected boolean onOK() {
        getFXEntity().setValue("ITARCALC", ITARCALC_COMBO.isSelected() ? 1L : 0L);
        return super.onOK();
    }
}

