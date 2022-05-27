package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
import ru.inversion.FXCalcBank.pojo.lov.PIkrbAccNameLov;
import ru.inversion.bicomp.stringconverter.BundleStringConverter;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvCheckBox;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.combobox.JInvComboBoxSimple;

import java.util.ResourceBundle;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author XDWeloper
 * @since Thu Feb 24 11:52:52 MSK 2022
 */
public class EditIkrbTypeevController extends JInvFXFormController<PIkrbTypeev> {

//    @FXML JInvLongField ITYPEEVEVID;
//    @FXML JInvTextField CTYPEEVNAME;
    @FXML JInvComboBoxSimple ITYPEEVTYPE;
    @FXML JInvCheckBox IPLTEVGR;
//    @FXML JInvLongField IRBNUM;
    @FXML JInvLongField ITYPEEV_ACCD;
    @FXML JInvLongField ITYPEEV_ACCC;
//    @FXML JInvTextField CTYPEEV_COD;


    @Override
    protected void init() throws Exception {
        super.init();
        ResourceBundle rb = ResourceBundle.getBundle("ru/inversion/FXCalcBank/controllers/res/ChooseComboITypeevType");
        ITYPEEVTYPE.setToolTipText(getBundleString("ITYPEEVTYPE.TOOL"));
        ITYPEEVTYPE.setConverter(new BundleStringConverter(rb));
        ITYPEEVTYPE.getItems().clear();
        ITYPEEVTYPE.getItems().addAll(rb.keySet());
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();

        if(getFormMode().equals(FormModeEnum.VM_INS))
            ITYPEEVTYPE.getSelectionModel().selectLast();
        else
            ITYPEEVTYPE.getSelectionModel().select(getDataObject().getITYPEEVTYPE());

        setLov(
                this,
                null,
                PIkrbAccNameLov.class,
                "ITYPE_ACC",
                "",
                "",
                ITYPEEV_ACCD,
                true,
                true,
                null
        );

        setLov(
                this,
                null,
                PIkrbAccNameLov.class,
                "ITYPE_ACC",
                "",
                "",
                ITYPEEV_ACCC,
                true,
                true,
                null
        );
        IPLTEVGR.setSelected(getDataObject().getIPLTEVGR() != null && getDataObject().getIPLTEVGR().equals(1L));
    }

    @Override
    protected boolean onOK() {
        getFXEntity().setValue("IPLTEVGR", IPLTEVGR.isSelected() ? 1L : null);
        return super.onOK();
    }
}

