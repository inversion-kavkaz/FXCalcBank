package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.action.OperationPropsAction;
import ru.inversion.FXCalcBank.pojo.PIkrbOp;
import ru.inversion.FXCalcBank.pojo.PVIkrbOpS;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.ActionFactory;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXBrowserController;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.meta.EntityMetadataFactory;
import ru.inversion.meta.IEntityProperty;

/**
 * @author XDWeloper
 * @since Tue Feb 22 16:50:37 MSK 2022
 */
public class ViewIkrbOpController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbOp> IKRB_OP;
    @FXML
    private JInvTable<PVIkrbOpS> V_IKRB_OP_S;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbOp> dsIKRB_OP = new XXIDataSet<>(getTaskContext(),PIkrbOp.class);
    private final XXIDataSet<PVIkrbOpS> dsV_IKRB_OP_S = new XXIDataSet<>(getTaskContext(),PVIkrbOpS.class);

    private String irbnum;
    private String ibankid;

    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        DSFXAdapter<PIkrbOp> dsfx = DSFXAdapter.bind(dsIKRB_OP, IKRB_OP, null, false);
        DSFXAdapter<PVIkrbOpS> dsfx1 = DSFXAdapter.bind(dsV_IKRB_OP_S, V_IKRB_OP_S, null, false);
        dsfx.setEnableFilter(true);
        dsfx1.setEnableFilter(true);


        initToolBar();

        IKRB_OP.setToolBar(toolBar);
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation(FormModeEnum.VM_NONE));
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_OP.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation1(FormModeEnum.VM_INS));
        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation1(FormModeEnum.VM_NONE));
        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation1(FormModeEnum.VM_SHOW));
        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation1(FormModeEnum.VM_EDIT));
        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation1(FormModeEnum.VM_DEL));
        V_IKRB_OP_S.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        irbnum = getInitProperties().get(OperationPropsAction.Params.IRB_NUM.name()) != null ? getInitProperties().get(OperationPropsAction.Params.IRB_NUM.name()).toString() : "null";
        ibankid = getInitProperties().get(OperationPropsAction.Params.IBANK_ID.name()) != null ? getInitProperties().get(OperationPropsAction.Params.IBANK_ID.name()).toString() : "null";
        dsIKRB_OP.setFilter("irbnum = " + irbnum, true,false);
        dsV_IKRB_OP_S.setFilter("ibankid = " + ibankid, true,false);
        DataLinkBuilder.linkDataSet(dsIKRB_OP,dsV_IKRB_OP_S,PIkrbOp::getIRBOPID,"IRBOPID",true);

        doRefresh();
    }

    private void doRefresh() {
        IKRB_OP.executeQuery();
    }

    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.CREATE_BY,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE);
    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbOp p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbOp();
                p.setIRBNUM(Long.parseLong(irbnum));
                break;
            case VM_NONE:
                if (dsIKRB_OP.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbOp();
                for (IEntityProperty<PIkrbOp, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbOp.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_OP.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_OP.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbOpController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbOp> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_OP.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_OP.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_OP.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_OP.requestFocus();
    }

    private void doOperation1(JInvFXFormController.FormModeEnum mode) {
        PVIkrbOpS p = null;

        switch (mode) {
            case VM_INS:
                p = new PVIkrbOpS();
                p.setIBANKID(Long.parseLong(ibankid));
                p.setIRBOPID(dsIKRB_OP.getCurrentRow().getIRBOPID());
                break;
            case VM_NONE:
                if (dsV_IKRB_OP_S.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PVIkrbOpS();
                for (IEntityProperty<PVIkrbOpS, ?> ep : EntityMetadataFactory.getEntityMetaData(PVIkrbOpS.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsV_IKRB_OP_S.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsV_IKRB_OP_S.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditVIkrbOpSController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult1)
                    .doModal();
    }

    private void doFormResult1(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PVIkrbOpS> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsV_IKRB_OP_S.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    try {
                        dsV_IKRB_OP_S.refreshCurrentRowFromDB();
                    } catch (DataSetException e) {
                        e.printStackTrace();
                    }
                    break;
                case VM_EDIT:
                    dsV_IKRB_OP_S.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsV_IKRB_OP_S.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        V_IKRB_OP_S.requestFocus();
    }


}

