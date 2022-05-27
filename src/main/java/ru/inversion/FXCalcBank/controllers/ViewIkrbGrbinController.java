package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbBin;
import ru.inversion.FXCalcBank.pojo.PIkrbGrbin;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.meta.EntityMetadataFactory;
import ru.inversion.meta.IEntityProperty;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;

import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;

/**
 *
 * @author  XDWeloper
 * @since   Fri May 27 12:55:43 MSK 2022
 */
public class ViewIkrbGrbinController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkrbGrbin> IKRB_GRBIN;
    @FXML private JInvTable<PIkrbBin> IKRB_BIN;
    @FXML private JInvToolBar toolBar;

    private final XXIDataSet<PIkrbGrbin> dsIKRB_GRBIN = new XXIDataSet<> (getTaskContext(),PIkrbGrbin.class);
    private final XXIDataSet<PIkrbBin> dsIKRB_BIN = new XXIDataSet<> (getTaskContext(),PIkrbBin.class);

    private void initDataSet () throws Exception
    {
        DataLinkBuilder.linkDataSet(dsIKRB_GRBIN,dsIKRB_BIN,PIkrbGrbin::getIGRBINID,"IGRBINID");
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkrbGrbin> dsfx = DSFXAdapter.bind (dsIKRB_GRBIN, IKRB_GRBIN, null, false); 
        dsfx.setEnableFilter (true);
        DSFXAdapter<PIkrbBin> dsfx1 = DSFXAdapter.bind (dsIKRB_BIN, IKRB_BIN, null, false);
        dsfx1.setEnableFilter (true);

        initToolBar ();

        IKRB_BIN.setToolBar (toolBar);
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IKRB_BIN.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        

    private void doRefresh ()
    {
        IKRB_GRBIN.executeQuery ();
    }

    private void initToolBar ()
    {
        JInvButtonPrint bp = new JInvButtonPrint (this::setPrintParam);
        bp.setReportTypeId (200000);
        toolBar.getItems ().add (bp);

        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.CREATE_BY,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE);
    }
    private void setPrintParam ( ApReport ap )
    {
        if (dsIKRB_BIN.isEmpty ())
            throw new StopExecuteActionBiCompException ();
    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PIkrbBin p = null;

        switch (mode) {
            case VM_INS:
                if(dsIKRB_GRBIN.getCurrentRow() == null) return;
                p = new PIkrbBin ();
                p.setIGRBINID(dsIKRB_GRBIN.getCurrentRow().getIGRBINID());
                break;
            case VM_NONE:
                if (dsIKRB_BIN.getCurrentRow () == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbBin ();
                for (IEntityProperty<PIkrbBin, ?> ep : EntityMetadataFactory.getEntityMetaData (PIkrbBin.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsIKRB_BIN.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_BIN.getCurrentRow ();
                break;
        }

        if (p != null)
            new FXFormLauncher<> (this, EditIkrbBinController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)
                .doModal ();
    }

    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbBin> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ())
            {
                case VM_INS:
                    dsIKRB_BIN.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_BIN.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIKRB_BIN.removeCurrentRow ();
                    break;
                default:
                    break;
            }
        }

        IKRB_BIN.requestFocus ();
    }
}

