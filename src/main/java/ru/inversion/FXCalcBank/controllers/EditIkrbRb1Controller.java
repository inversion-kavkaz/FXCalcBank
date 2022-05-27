package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PDgRef;
import ru.inversion.FXCalcBank.pojo.PIkrbRb;
import ru.inversion.bicomp.stringconverter.DataSetStringConverter;
import ru.inversion.dataset.SQLDataSet;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.combobox.JInvComboBoxSimple;

/**
 * @author XDWeloper
 * @since Tue Feb 15 10:44:30 MSK 2022
 */
public class EditIkrbRb1Controller extends JInvFXFormController<PIkrbRb> {
    //
//
//
//    @FXML JInvLongField IRBNUM;
//    @FXML JInvTextField CRBNAME;
//    @FXML JInvLongField IRBSHEMCOR;
    @FXML
    JInvComboBoxSimple IRBPROCTYPE;
//    @FXML JInvTextField IDSMR;
//    @FXML JInvLongField IRBEXTENDID;
//    @FXML JInvTextField CUSRLOGNAME;
//    @FXML JInvTextField CBIC;

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        super.init();
        SQLDataSet<PDgRef> populateDataSet = populateDataSet(PDgRef.class, "select IREFERENCENUM  ,CREFERENCESHORT from dg_ref", "CREFERENCETYPE = 'PL_PROC'", null, 2);
        DataSetStringConverter<PDgRef, Long> sc = new DataSetStringConverter<>(populateDataSet, PDgRef::getIREFERENCENUM, PDgRef::getCREFERENCESHORT);
        IRBPROCTYPE.setConverter(sc);
        IRBPROCTYPE.getItems().clear();
        IRBPROCTYPE.getItems().addAll(sc.keySet());
    }

}

