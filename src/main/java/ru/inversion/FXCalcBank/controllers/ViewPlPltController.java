package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PPlPlt;
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
 * @author  Komp
 * @since   Wed May 18 17:29:12 MSK 2022
 */
public class ViewPlPltController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PPlPlt> PL_PLT;   
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<PPlPlt> dsPL_PLT = new XXIDataSet<> ();    
//
// initDataSet
//    
    private void initDataSet () throws Exception 
    {
        dsPL_PLT.setTaskContext (getTaskContext ());
        dsPL_PLT.setRowClass (PPlPlt.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PPlPlt> dsfx = DSFXAdapter.bind (dsPL_PLT, PL_PLT, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        PL_PLT.setToolBar (toolBar);       
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        PL_PLT.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
// doRefresh
//    
    private void doRefresh () 
    {
        PL_PLT.executeQuery ();
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
        if (dsPL_PLT.isEmpty ())
            throw new StopExecuteActionBiCompException ();
    }
//
// doOperation
//
    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PPlPlt p = null;

        switch (mode) {
            case VM_INS:
                p = new PPlPlt ();
                break;
            case VM_NONE:
                if (dsPL_PLT.getCurrentRow () == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PPlPlt ();
                for (IEntityProperty<PPlPlt, ?> ep : EntityMetadataFactory.getEntityMetaData (PPlPlt.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsPL_PLT.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsPL_PLT.getCurrentRow ();
                break;
        }

//        if (p != null)
//            new FXFormLauncher<> (this, EditPlPltController.class)
//                .dataObject (p)
//                .dialogMode (mode)
//                .initProperties (getInitProperties ())
//                .callback (this::doFormResult)
//                .doModal ();
    }
//
// doFormResult
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PPlPlt> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ())
            {
                case VM_INS:
                    dsPL_PLT.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsPL_PLT.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsPL_PLT.removeCurrentRow ();
                    break;
                default:
                    break;
            }
        }

        PL_PLT.requestFocus ();
    }
}

