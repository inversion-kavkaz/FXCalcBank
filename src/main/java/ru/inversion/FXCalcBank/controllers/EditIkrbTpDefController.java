package ru.inversion.FXCalcBank.controllers;



import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTpDef;
import ru.inversion.FXCalcBank.pojo.lov.PDgRef;
import ru.inversion.FXCalcBank.pojo.lov.PTop;
import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvCheckBox;
import ru.inversion.fx.form.controls.JInvLongField;
import ru.inversion.fx.form.controls.JInvTextArea;
import ru.inversion.fx.form.controls.JInvTextField;
import ru.inversion.fx.form.lov.JInvEntityLov;
import ru.inversion.utils.U;

import static ru.inversion.FXCalcBank.utils.Utils.setLov;

/**
 * @author  Komp
 * @since   Fri Jun 17 10:56:03 MSK 2022
 */
public class EditIkrbTpDefController extends JInvFXFormController <PIkrbTpDef>
{  
//
//
//
//    @FXML JInvLongField IPLTDDEFID;
//    @FXML JInvLongField IPLTPDEFID;
//    @FXML JInvTextField CPLTPVAL;
    @FXML JInvLongField IPLTPTYPE;
    @FXML JInvTextField IPLTPTYPENAME;
//    @FXML JInvLongField IPLTPCALC;
    @FXML JInvTextArea CPLTPCOD;
//    @FXML JInvLongField IPLREFID;
    @FXML JInvCheckBox IPLTPCALC_CHECK;

    @Override
    protected void init () throws Exception 
    {
        super.init (); 
    }


    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        final JInvEntityLov iplpTypeNameLov = setLov(
                this,
                "Выбрать!",
                PDgRef.class,
                "IREFERENCENUM",
                "creferencetype ='PL_TP' AND IREFERENCENUM IN (2) ",
                "IREFERENCENUM",
                IPLTPTYPE,
                true,
                true,
                null
        );
        iplpTypeNameLov.bindControl(IPLTPTYPENAME,param -> ((PDgRef)param).getCREFERENCENAME());

        IPLTPCALC_CHECK.setSelected(U.nvl(getDataObject().getIPLTPCALC(),0L) != 0L);
    }

    @Override
    protected boolean onOK() {
        getFXEntity().setValue("IPLTPCALC", IPLTPCALC_CHECK.isSelected() ? 1L : 0L);
        return super.onOK();
    }
}

