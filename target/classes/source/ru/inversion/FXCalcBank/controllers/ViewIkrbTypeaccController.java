package ru.inversion.FXCalcBank.controllers;

import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeacc;
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
 *
 * @author  XDWeloper
 * @since   Wed Feb 09 10:16:31 MSK 2022
 */
public class ViewIkrbTypeaccController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkrbTypeacc> IKRB_TYPEACC;
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<PIkrbTypeacc> dsIKRB_TYPEACC = new XXIDataSet<> ();    
//
// initDataSet
//    
    private void initDataSet () {
        dsIKRB_TYPEACC.setTaskContext (getTaskContext ());
        dsIKRB_TYPEACC.setRowClass (PIkrbTypeacc.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkrbTypeacc> dsfx = DSFXAdapter.bind (dsIKRB_TYPEACC, IKRB_TYPEACC, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        IKRB_TYPEACC.setToolBar (toolBar);       
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IKRB_TYPEACC.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
// doRefresh
//    
    private void doRefresh () 
    {
        IKRB_TYPEACC.executeQuery ();
    }
//
// initToolBar
//    
    private void initToolBar () 
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE,
                                    ActionFactory.ActionTypeEnum.CREATE_BY, 
                                    ActionFactory.ActionTypeEnum.VIEW,
                                    ActionFactory.ActionTypeEnum.UPDATE,
                                    ActionFactory.ActionTypeEnum.DELETE);
    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PIkrbTypeacc p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbTypeacc ();
                break;
            case VM_NONE:
                if (dsIKRB_TYPEACC.getCurrentRow () == null) 
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbTypeacc ();
                for (IEntityProperty<PIkrbTypeacc, ?> ep : EntityMetadataFactory.getEntityMetaData (PIkrbTypeacc.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsIKRB_TYPEACC.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_TYPEACC.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<> (this, EditIkrbTypeaccController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .doModal ();
    }
//
// doFormResult 
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbTypeacc> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIKRB_TYPEACC.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIKRB_TYPEACC.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIKRB_TYPEACC.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IKRB_TYPEACC.requestFocus ();
    }        
//
//
//    
}

