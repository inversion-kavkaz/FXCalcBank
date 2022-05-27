package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PDgRef;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
import ru.inversion.FXCalcBank.pojo.lov.PIkrbAccNameLov;
import ru.inversion.bicomp.stringconverter.BundleStringConverter;
import ru.inversion.bicomp.stringconverter.DataSetStringConverter;
import ru.inversion.dataset.SQLDataSet;
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
    @FXML JInvComboBoxSimple IPLTEVGR;
//    @FXML JInvLongField IRBNUM;
    @FXML JInvLongField ITYPEEV_ACCD;
    @FXML JInvLongField ITYPEEV_ACCC;
//    @FXML JInvTextField CTYPEEV_COD;


    @Override
    protected void init() throws Exception {
        super.init();
        initComboBoxConverter();
    }

    private void initComboBoxConverter() throws ru.inversion.dataset.DataSetException {
        //---------------------------------------------------------------------------------------------------------
        ITYPEEVTYPE.setToolTipText(getBundleString("ITYPEEVTYPE.TOOL"));
        SQLDataSet<PDgRef> populateDataSet = populateDataSet(PDgRef.class,"select * from dg_ref where creferencetype = 'IKRB_FINEV' order by ireferencenum",null,null, 2);
        DataSetStringConverter<PDgRef, Long> sc = new DataSetStringConverter<>(populateDataSet,PDgRef::getIREFERENCENUM,PDgRef::getCREFERENCESHORT);
        ITYPEEVTYPE.setConverter(sc);
        ITYPEEVTYPE.getItems().clear();
        ITYPEEVTYPE.getItems().addAll(sc.keySet());
        ITYPEEVTYPE.valueProperty().addListener((observable, oldValue, newValue) -> {});
//---------------------------------------------------------------------------------------------------------
        IPLTEVGR.setToolTipText(getBundleString("IPLTEVGR.TOOL"));
        SQLDataSet<PDgRef> populateDataSet1 = populateDataSet(PDgRef.class,"select * from dg_ref where creferencetype = 'IKRB_OUTEV' order by ireferencenum",null,null, 2);
        DataSetStringConverter<PDgRef, Long> sc1 = new DataSetStringConverter<>(populateDataSet1,PDgRef::getIREFERENCENUM,PDgRef::getCREFERENCESHORT);
        IPLTEVGR.setConverter(sc1);
        IPLTEVGR.getItems().clear();
        IPLTEVGR.getItems().addAll(sc.keySet());
        IPLTEVGR.valueProperty().addListener((observable, oldValue, newValue) -> {});
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

    }
}

