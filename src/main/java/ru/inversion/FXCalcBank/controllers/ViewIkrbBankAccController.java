package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.action.AccEmployeeDictionaryAction;
import ru.inversion.FXCalcBank.pojo.PIkrbBankAcc;
import ru.inversion.FXCalcBank.pojo.PIkrbRbAcc;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
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
 * @since Wed Feb 09 10:21:04 MSK 2022
 */
public class ViewIkrbBankAccController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbBankAcc> IKRB_BANK_ACC;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbBankAcc> dsIKRB_BANK_ACC = new XXIDataSet<>(getTaskContext(), PIkrbBankAcc.class);

    private void initDataSet() {
        Object obj = getInitProperties().get(AccEmployeeDictionaryAction.Params.IBANKID.name());
        if(obj != null)
        {
            Long IBANKID = (Long)obj;
            dsIKRB_BANK_ACC.setFilter("IBANKID = " + IBANKID, true, false);
        }

    }


    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbBankAcc> dsfx = DSFXAdapter.bind(dsIKRB_BANK_ACC, IKRB_BANK_ACC, null, false);

        dsfx.setEnableFilter(true);


        initToolBar();

        IKRB_BANK_ACC.setToolBar(toolBar);
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation(FormModeEnum.VM_NONE));
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_BANK_ACC.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    //
// doRefresh
//    
    private void doRefresh() {
        IKRB_BANK_ACC.executeQuery();
    }

    //
// initToolBar
//    
    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.CREATE_BY,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE);
    }

    //
// setPrintParam
//
    private void setPrintParam(ApReport ap) {
        if (dsIKRB_BANK_ACC.isEmpty())
            throw new StopExecuteActionBiCompException();
    }

    //
// doOperation
//    
    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbBankAcc p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbBankAcc();
                break;
            case VM_NONE:
                if (dsIKRB_BANK_ACC.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbBankAcc();
                for (IEntityProperty<PIkrbBankAcc, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbBankAcc.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_BANK_ACC.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_BANK_ACC.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbBankAccController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    //
// doFormResult 
//
    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbBankAcc> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_BANK_ACC.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_BANK_ACC.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_BANK_ACC.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_BANK_ACC.requestFocus();
    }
//
//
//    
}

