package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTdDef;
import ru.inversion.FXCalcBank.pojo.PIkrbTpDef;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.ActionFactory;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXBrowserController;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;

/**
 * @author Komp
 * @since Fri Jun 17 10:54:19 MSK 2022
 */
public class ViewIkrbTdDefController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbTdDef> IKRB_TD_DEF;
    @FXML
    private JInvTable<PIkrbTpDef> IKRB_TP_DEF;

    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbTdDef> dsIKRB_TD_DEF = new XXIDataSet();
    private final XXIDataSet<PIkrbTpDef> dsIKRB_TP_DEF = new XXIDataSet();

    private void initDataSet() throws Exception {
        dsIKRB_TD_DEF.setTaskContext(getTaskContext());
        dsIKRB_TD_DEF.setRowClass(PIkrbTdDef.class);
        dsIKRB_TP_DEF.setTaskContext(getTaskContext());
        dsIKRB_TP_DEF.setRowClass(PIkrbTpDef.class);
        DataLinkBuilder.linkDataSet(dsIKRB_TD_DEF, dsIKRB_TP_DEF, PIkrbTdDef::getIPLTDDEFID, "IPLTDDEFID");
    }

    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbTdDef> dsfx = DSFXAdapter.bind(dsIKRB_TD_DEF, IKRB_TD_DEF, null, false);
        DSFXAdapter<PIkrbTpDef> dsfx1 = DSFXAdapter.bind(dsIKRB_TP_DEF, IKRB_TP_DEF, null, false);

        dsfx.setEnableFilter(true);
        dsfx1.setEnableFilter(true);


        initToolBar();

        IKRB_TD_DEF.setToolBar(toolBar);
        IKRB_TD_DEF.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_TD_DEF.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_TD_DEF.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_TD_DEF.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_TD_DEF.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        IKRB_TP_DEF.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation1(FormModeEnum.VM_INS));
        IKRB_TP_DEF.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation1(FormModeEnum.VM_SHOW));
        IKRB_TP_DEF.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation1(FormModeEnum.VM_EDIT));
        IKRB_TP_DEF.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation1(FormModeEnum.VM_DEL));
        IKRB_TP_DEF.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());


        doRefresh();
    }

    private void doRefresh() {
        IKRB_TD_DEF.executeQuery();
        IKRB_TD_DEF.requestFocus();
    }

    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE,
                ActionFactory.ActionTypeEnum.REFRESH);

    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbTdDef p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTdDef();
                Long ITYPEEVEVID = (Long)getInitProperties().get("ITYPEEVEVID");
                p.setIPLT_EVENT(ITYPEEVEVID);
                p.setIPLT_SHEMID(1L);
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_TD_DEF.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<PIkrbTdDef>(getTaskContext(), getViewContext(), EditIkrbTdDefController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .modal(true)
                    .show();
    }

    private void doOperation1(JInvFXFormController.FormModeEnum mode) {
        PIkrbTpDef p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTpDef();
                p.setIPLTDDEFID(dsIKRB_TD_DEF.getCurrentRow().getIPLTDDEFID());
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_TP_DEF.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<PIkrbTpDef>(getTaskContext(), getViewContext(), EditIkrbTpDefController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult1)
                    .modal(true)
                    .show();
    }

    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbTdDef> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_TD_DEF.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_TD_DEF.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_TD_DEF.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_TD_DEF.requestFocus();
    }

    private void doFormResult1(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbTpDef> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_TP_DEF.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_TP_DEF.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_TP_DEF.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_TP_DEF.requestFocus();
    }
}

