package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbRb;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.meta.EntityMetadataFactory;
import ru.inversion.meta.IEntityProperty;

/**
 * @author XDWeloper
 * @since Thu Feb 10 16:03:27 MSK 2022
 */
public class ViewIkrbRbController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbRb> IKRB_RB;
    @FXML
    private JInvTable<PIkrbTypeev> IKRB_TYPEEV;
    @FXML
    private JInvToolBar toolBar;

    private final XXIDataSet<PIkrbRb> dsIKRB_RB = new XXIDataSet<>(getTaskContext(), PIkrbRb.class);
    private final XXIDataSet<PIkrbTypeev> dsIKRB_TYPEEV = new XXIDataSet<>(getTaskContext(), PIkrbTypeev.class);

    {
        DataLinkBuilder.linkDataSet(dsIKRB_RB, dsIKRB_TYPEEV, PIkrbRb::getIRBNUM, "IRBNUM");
    }


    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        DSFXAdapter<PIkrbRb> dsfx = DSFXAdapter.bind(dsIKRB_RB, IKRB_RB, null, false);
        DSFXAdapter<PIkrbTypeev> dsfx1 = DSFXAdapter.bind(dsIKRB_TYPEEV, IKRB_TYPEEV, null, false);
        dsfx.setEnableFilter(true);
        dsfx1.setEnableFilter(true);

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

    private void doRefresh() {
        IKRB_RB.executeQuery();
    }

    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.CREATE_BY,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE);
    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkrbTypeev p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTypeev();
                if(dsIKRB_RB.getCurrentRow() == null)
                    throw new AlertException("Операция не возможна");
                p.setIRBNUM(dsIKRB_RB.getCurrentRow().getIRBNUM());
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


}

