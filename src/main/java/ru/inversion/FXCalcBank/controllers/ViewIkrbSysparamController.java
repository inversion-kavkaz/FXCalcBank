package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbSysparam;
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
 * @since Tue Feb 22 12:27:27 MSK 2022
 */
public class ViewIkrbSysparamController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbSysparam> IKRB_SYSPARAM;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkrbSysparam> dsIKRB_SYSPARAM = new XXIDataSet<>();

    private void initDataSet() throws Exception {
        dsIKRB_SYSPARAM.setTaskContext(getTaskContext());
        dsIKRB_SYSPARAM.setRowClass(PIkrbSysparam.class);
    }

    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkrbSysparam> dsfx = DSFXAdapter.bind(dsIKRB_SYSPARAM, IKRB_SYSPARAM, null, false);
        dsfx.setEnableFilter(true);
        initToolBar();
        IKRB_SYSPARAM.setToolBar(toolBar);
        IKRB_SYSPARAM.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IKRB_SYSPARAM.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());
        doRefresh();
    }

    private void doRefresh() {
        IKRB_SYSPARAM.executeQuery();
    }

    private void initToolBar() {

        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.UPDATE);
    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbSysparam p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbSysparam();
                break;
            case VM_NONE:
                if (dsIKRB_SYSPARAM.getCurrentRow() == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbSysparam();
                for (IEntityProperty<PIkrbSysparam, ?> ep : EntityMetadataFactory.getEntityMetaData(PIkrbSysparam.class).getPropertiesMap().values())
                    if (!(ep.isTransient() || ep.isId()))
                        ep.invokeSetter(p, ep.invokeGetter(dsIKRB_SYSPARAM.getCurrentRow()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_SYSPARAM.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<>(this, EditIkrbSysparamController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .doModal();
    }

    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbSysparam> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIKRB_SYSPARAM.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_SYSPARAM.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIKRB_SYSPARAM.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IKRB_SYSPARAM.requestFocus();
    }
}

