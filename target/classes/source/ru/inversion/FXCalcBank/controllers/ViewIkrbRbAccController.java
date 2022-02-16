package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.action.AllDictionaryAction;
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

import java.util.Map;

/**
 * @author XDWeloper
 * @since Wed Feb 09 10:19:51 MSK 2022
 */
public class ViewIkrbRbAccController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbRbAcc> IKRB_RB_ACC;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbRbAcc> dsIKRB_RB_ACC = new XXIDataSet<>(getTaskContext(), PIkrbRbAcc.class);

    private void initDataSet() {
        Object obj = getInitProperties().get(AllDictionaryAction.Params.IRBNUM.name());
        if(obj != null)
        {
            Long irbNum = (Long)obj;
            dsIKRB_RB_ACC.setFilter("IRBNUM = " + irbNum, true, false);
        }
    }

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbRbAcc> dsfx = DSFXAdapter.bind(dsIKRB_RB_ACC, IKRB_RB_ACC, null, false);

        dsfx.setEnableFilter(true);


        initToolBar();

        IKRB_RB_ACC.setToolBar(toolBar);
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation(FormModeEnum.VM_NONE));
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_RB_ACC.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    //
// doRefresh
//    
    private void doRefresh() {
        IKRB_RB_ACC.executeQuery();
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

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbRbAcc p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbRbAcc();
                break;
            case VM_NONE:
                if (dsIKRB_RB_ACC.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbRbAcc();
                for (IEntityProperty<PIkrbRbAcc, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbRbAcc.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_RB_ACC.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_RB_ACC.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbRbAccController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    //
// doFormResult 
//
    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbRbAcc> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_RB_ACC.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_RB_ACC.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_RB_ACC.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_RB_ACC.requestFocus();
    }
//
//
//    
}

