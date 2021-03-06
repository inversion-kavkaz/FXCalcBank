package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.action.CommissionProcessingAction;
import ru.inversion.FXCalcBank.pojo.PIkrbPack;
import ru.inversion.FXCalcBank.pojo.PIkrbe;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.icons.enums.FontAwesome;

/**
 * @author Komp
 * @since Fri May 20 11:34:16 MSK 2022
 */
public class ViewIkrbPackController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbPack> IKRB_PACK;
    @FXML
    private JInvToolBar toolBar;
    @FXML
    private JInvTable<PIkrbe> IKRBE;

    private final XXIDataSet<PIkrbPack> dsIKRB_PACK = new XXIDataSet<>(getTaskContext(), PIkrbPack.class);
    private final XXIDataSet<PIkrbe> dsIKRBE = new XXIDataSet<>(getTaskContext(), PIkrbe.class);

    private ViewIkrbFileInController viewIkrbFileInController;

    private void initDataSet() {
        dsIKRB_PACK.setFilter(" DPACKDATE = TRUNC(SYSDATE)", false, false);
        DataLinkBuilder.linkDataSet(dsIKRB_PACK, dsIKRBE, PIkrbPack::getIPACKID, "IPACKID");
    }

    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));
        viewIkrbFileInController = (ViewIkrbFileInController) getInitProperties().get(CommissionProcessingAction.Params.callBack.name());

        initDataSet();
        DSFXAdapter<PIkrbPack> dsfx = DSFXAdapter.bind(dsIKRB_PACK, IKRB_PACK, null, false);
        DSFXAdapter<PIkrbe> dsfx1 = DSFXAdapter.bind(dsIKRBE, IKRBE, null, false);
        dsfx.setEnableFilter(true);
        dsfx1.setEnableFilter(true);
        initToolBar();
        doRefresh();
    }

    private void doRefresh() {
        IKRB_PACK.executeQuery();
    }

    private void initToolBar() {
        JInvButtonPrint bp = new JInvButtonPrint(this::setPrintParam);
        bp.setReportTypeId(9999999002d);

        toolBar.getItems().add(ActionFactory.createButton(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh()));
        toolBar.getItems().add(bp);

        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_file_o, "??????????", event -> newPackage(), "??????????"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_arrow_right, "??????????????????", event -> send(), "??????????????????"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_bug, "????????????????", event -> trn(), "??????????????????"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_remove, "????????????????", event -> cancel(), "????????????????"));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_calendar_check_o, "?????????? ????????????", event -> endMount(), "?????????? ????????????"));
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }

    private void setPrintParam(ApReport ap) {
        if (dsIKRB_PACK.isEmpty())
            throw new StopExecuteActionBiCompException();
    }

    private void trn() {
        if (dsIKRB_PACK.getCurrentRow() == null) return;
        Long result;
        String msg = "";
        try {
            ParamMap p = new ParamMap().add("fileinid", dsIKRB_PACK.getCurrentRow().getIPACKID()).exec(this, "make_complt");
            result = p.getLong("res");
            msg = p.getString("MSG");

            if (result > 0)
                new FXFormLauncher<>(this, ViewPlPltController.class)
                        .callback(this::doTrnResult)
                        .doModal();
            else if (result == 0) {
                try {
                    new ParamMap().exec(this, "plt2trnMain");
                    Alerts.info(this, "????????????????????", msg);
                } catch (SQLExpressionException e) {
                    throw new AlertException(e);
                }
            } else if(result < 0){
                throw new AlertException(msg);
            }
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
    }

    private void doTrnResult(FormReturnEnum formReturnEnum, JInvFXFormController<Object> objectJInvFXFormController) {
        try {
            new ParamMap().exec(this, formReturnEnum.equals(FormReturnEnum.RET_OK) ? "plt2trnMain" : "recall_plt_d");
            if (formReturnEnum.equals(FormReturnEnum.RET_OK))
                Alerts.info(this, "????????????????????", "???????????????? ?????????????????? ??????????????");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
    }

    private void newPackage() {
        Long result;
        try {
            ParamMap p = new ParamMap().exec(this, "create_new_pack");
            result = p.getLong("res");
            doRefresh();
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if (result.equals(1L)) {
            Alerts.info(this, "????????????????????", "???????????????? ???????????? ??????????????.");
        }
    }

    private void send() {
        Long result;
        String msg = "";
        if (dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().add("ipackid", ipackid).exec(this, "send_pack");
            result = p.getLong("res");
            msg = p.getString("MSG");

            if (result > 0) {
                viewIkrbFileInController.uploadFiles();
                dsIKRB_PACK.refreshCurrentDependentData();
            }
            Alerts.info(this, "????????????????????", msg);
        } catch (SQLExpressionException | DataSetException e) {
            throw new AlertException(e);
        }
    }

    private void cancel() {
        Long result;
        if (dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().add("ipackid", ipackid).exec(this, "rollback_pack");
            result = p.getLong("res");
            doRefresh();
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if (result.equals(1L)) {
            Alerts.info(this, "????????????????????", "???????????????? ???????????? ??????????????.");
            //dsIKRB_PACK.refreshCurrentDependentData();
        }
    }

    private void endMount() {
        Long result;
        if (dsIKRB_PACK.getCurrentRow() == null) return;
        Long ipackid = dsIKRB_PACK.getCurrentRow().getIPACKID();
        try {
            ParamMap p = new ParamMap().exec(this, "end_month_komis");
            result = p.getLong("res");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
        if (result.equals(1L))
            Alerts.info(this, "????????????????????", "???????????????? ???????????? ??????????????.");
    }

}

