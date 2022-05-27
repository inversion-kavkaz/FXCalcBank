package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PVIkrbOpS;
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
 * @since   Tue Feb 22 16:51:17 MSK 2022
 */
public class ViewVIkrbOpSController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PVIkrbOpS> V_IKRB_OP_S;
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<PVIkrbOpS> dsV_IKRB_OP_S = new XXIDataSet<> ();    
//
// initDataSet
//    
    private void initDataSet () throws Exception 
    {
        dsV_IKRB_OP_S.setTaskContext (getTaskContext ());
        dsV_IKRB_OP_S.setRowClass (PVIkrbOpS.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PVIkrbOpS> dsfx = DSFXAdapter.bind (dsV_IKRB_OP_S, V_IKRB_OP_S, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        V_IKRB_OP_S.setToolBar (toolBar);       
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        V_IKRB_OP_S.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
// doRefresh
//    
    private void doRefresh () 
    {
        V_IKRB_OP_S.executeQuery ();
    }
//
// initToolBar
//    
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
//
// setPrintParam
//
    private void setPrintParam ( ApReport ap ) 
    {
        if (dsV_IKRB_OP_S.isEmpty ())
            throw new StopExecuteActionBiCompException ();
    }
//
// doOperation
//    
    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        PVIkrbOpS p = null;

        switch (mode) {
            case VM_INS:
                p = new PVIkrbOpS ();
                break;
            case VM_NONE:
                if (dsV_IKRB_OP_S.getCurrentRow () == null) 
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PVIkrbOpS ();
                for (IEntityProperty<PVIkrbOpS, ?> ep : EntityMetadataFactory.getEntityMetaData (PVIkrbOpS.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsV_IKRB_OP_S.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsV_IKRB_OP_S.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<> (this, EditVIkrbOpSController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .doModal ();
    }
//
// doFormResult 
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PVIkrbOpS> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsV_IKRB_OP_S.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsV_IKRB_OP_S.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsV_IKRB_OP_S.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        V_IKRB_OP_S.requestFocus ();
    }        
//
//
//    
}

