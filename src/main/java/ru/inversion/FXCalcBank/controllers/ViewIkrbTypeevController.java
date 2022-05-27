package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
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
 * @since Thu Feb 24 11:52:46 MSK 2022
 */
public class ViewIkrbTypeevController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbTypeev> IKRB_TYPEEV;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbTypeev> dsIKRB_TYPEEV = new XXIDataSet<>();

    //
// initDataSet
//    
    private void initDataSet() throws Exception {
        dsIKRB_TYPEEV.setTaskContext(getTaskContext());
        dsIKRB_TYPEEV.setRowClass(PIkrbTypeev.class);
    }

    //
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbTypeev> dsfx = DSFXAdapter.bind(dsIKRB_TYPEEV, IKRB_TYPEEV, null, false);

        dsfx.setEnableFilter(true);


        initToolBar();

        IKRB_TYPEEV.setToolBar(toolBar);
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation(FormModeEnum.VM_NONE));
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_TYPEEV.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    //
// doRefresh
//    
    private void doRefresh() {
        IKRB_TYPEEV.executeQuery();
    }

    //
// initToolBar
//    
    private void initToolBar() {
        JInvButtonPrint bp = new JInvButtonPrint(this::setPrintParam);
        bp.setReportTypeId(200000);
        toolBar.getItems().add(bp);

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
        if (dsIKRB_TYPEEV.isEmpty())
            throw new StopExecuteActionBiCompException();
    }

    //
// doOperation
//    
    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbTypeev p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTypeev();
                break;
            case VM_NONE:
                if (dsIKRB_TYPEEV.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbTypeev();
                for (IEntityProperty<PIkrbTypeev, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbTypeev.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_TYPEEV.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_TYPEEV.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbTypeevController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    //
// doFormResult 
//
    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbTypeev> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_TYPEEV.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_TYPEEV.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_TYPEEV.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_TYPEEV.requestFocus();
    }
//
//
//    
}

