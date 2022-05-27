package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTarScale;
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
 * @since Thu Feb 10 15:33:00 MSK 2022
 */
public class ViewIkrbTarScaleController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbTarScale> IKRB_TAR_SCALE;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbTarScale> dsIKRB_TAR_SCALE = new XXIDataSet<>();

    private void initDataSet() throws Exception {
        dsIKRB_TAR_SCALE.setTaskContext(getTaskContext());
        dsIKRB_TAR_SCALE.setRowClass(PIkrbTarScale.class);
    }

    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbTarScale> dsfx = DSFXAdapter.bind(dsIKRB_TAR_SCALE, IKRB_TAR_SCALE, null, false);

        dsfx.setEnableFilter(true);


        initToolBar();

        IKRB_TAR_SCALE.setToolBar(toolBar);
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation(FormModeEnum.VM_NONE));
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IKRB_TAR_SCALE.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    private void doRefresh() {
        IKRB_TAR_SCALE.executeQuery();
    }

    private void initToolBar() {

        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.CREATE_BY,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE);
    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbTarScale p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTarScale();
                break;
            case VM_NONE:
                if (dsIKRB_TAR_SCALE.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbTarScale();
                for (IEntityProperty<PIkrbTarScale, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbTarScale.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_TAR_SCALE.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_TAR_SCALE.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbTarScaleController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbTarScale> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_TAR_SCALE.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_TAR_SCALE.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_TAR_SCALE.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_TAR_SCALE.requestFocus();
    }
}

