package ru.inversion.FXCalcBank.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.PIkrbTmpFile;
import ru.inversion.FXCalcBank.action.CommissionProcessingAction;
import ru.inversion.FXCalcBank.pojo.PIkrbPack;
import ru.inversion.FXCalcBank.pojo.PIkrbSysparam;
import ru.inversion.FXCalcBank.pojo.PIkrbe;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.*;
import ru.inversion.dataset.fx.DSFXAdapter;

import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversion.icons.enums.FontAwesome;

import java.util.Map;

/**
 *
 * @author  Komp
 * @since   Fri May 20 11:34:16 MSK 2022
 */
public class ViewIkrbPackController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkrbPack> IKRB_PACK;   
    @FXML private JInvToolBar toolBar;
    @FXML private JInvTable<PIkrbe> IKRBE;

    private final XXIDataSet<PIkrbPack> dsIKRB_PACK = new XXIDataSet<>(getTaskContext(),PIkrbPack.class);
    private final XXIDataSet<PIkrbe> dsIKRBE = new XXIDataSet<> (getTaskContext(),PIkrbe.class);

    private ViewIkrbFileInController viewIkrbFileInController;

    private void initDataSet ()
    {
        DataLinkBuilder.linkDataSet(dsIKRB_PACK, dsIKRBE, PIkrbPack::getIPACKID, "IMSGAPPID");
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        viewIkrbFileInController = (ViewIkrbFileInController)getInitProperties().get(CommissionProcessingAction.Params.callBack.name());

        initDataSet ();
        DSFXAdapter<PIkrbPack> dsfx = DSFXAdapter.bind (dsIKRB_PACK, IKRB_PACK, null, false);
        DSFXAdapter<PIkrbe> dsfx1 = DSFXAdapter.bind (dsIKRBE, IKRBE, null, false);
        dsfx.setEnableFilter (true);
        dsfx1.setEnableFilter (true);
        initToolBar();
        doRefresh ();
    }

    private void doRefresh () 
    {
        IKRB_PACK.executeQuery ();
    }

    private void initToolBar ()
    {
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_file_o, "Новый",event -> newPackage(), "Новый"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_arrow_right, "Отправить",event -> send(), "Отправить"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_remove, "Откатить",event -> cancel(), "Откатить"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_calendar_check_o, "Конец месяца",event -> endMount(), "Конец месяца"));
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }

    private void newPackage(){
        Long result;
        try {
            ParamMap p = new ParamMap().exec(this,"create_new_pack");
            result = p.getLong("res");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if(result.equals(1L)) {
            Alerts.info(this, "Информация", "Операция прошла успешно.");
            doRefresh();
        }
    }

    private void send() {
        Long result;
        if(dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().add("ipackid",ipackid).exec(this,"send_pack");
            result = p.getLong("res");
            if(result > 0) {
                viewIkrbFileInController.uploadFiles();
                Alerts.info(this, "Информация", "Операция прошла успешно.");
                dsIKRB_PACK.refreshCurrentDependentData();
            }
        } catch (SQLExpressionException | DataSetException e) {
            throw new AlertException(e);
        }
    }

    private void cancel() {
        Long result;
        if(dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().add("ipackid",ipackid).exec(this,"rollback_pack");
            result = p.getLong("res");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if(result.equals(1L)) {
            Alerts.info(this, "Информация", "Операция прошла успешно.");
            dsIKRB_PACK.refreshCurrentDependentData();
        }
    }

    private void endMount() {
        Long result;
        if(dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().exec(this,"end_month_komis");
            result = p.getLong("res");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if(result.equals(1L))
            Alerts.info(this,"Информация", "Операция прошла успешно.");
    }

}

