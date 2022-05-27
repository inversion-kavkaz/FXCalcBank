package ru.inversion.FXCalcBank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.action.AccEmployeeDictionaryAction;
import ru.inversion.FXCalcBank.action.AllDictionaryAction;
import ru.inversion.FXCalcBank.action.OperationPropsAction;
import ru.inversion.FXCalcBank.pojo.PIkrbBank;
import ru.inversion.FXCalcBank.pojo.PIkrbRb;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;
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
 * @since   Tue Feb 15 10:44:24 MSK 2022
 */
public class ViewIkrbRb1Controller extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkrbRb> IKRB_RB;
    @FXML private JInvTable<PIkrbBank> IKRB_BANK;

    @FXML private JInvToolBar toolBar;

 
    private final XXIDataSet<PIkrbRb> dsIKRB_RB = new XXIDataSet<> (getTaskContext(), PIkrbRb.class);
    private final XXIDataSet<PIkrbBank> dsIKRB_BANK = new XXIDataSet<> (getTaskContext(), PIkrbBank.class);

    {
        DataLinkBuilder.linkDataSet(dsIKRB_RB,dsIKRB_BANK,PIkrbRb::getIRBNUM, "IRBNUM");
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        DSFXAdapter<PIkrbRb> dsfx = DSFXAdapter.bind (dsIKRB_RB, IKRB_RB, null, false);
        DSFXAdapter<PIkrbBank> dsfx1 = DSFXAdapter.bind (dsIKRB_BANK, IKRB_BANK, null, false);
        dsfx.setEnableFilter (true);
        dsfx1.setEnableFilter (true);

                
        initToolBar ();

        IKRB_RB.setToolBar (toolBar);       
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IKRB_RB.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        IKRB_BANK.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation1 (FormModeEnum.VM_INS));
        IKRB_BANK.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation1 (FormModeEnum.VM_NONE));
        IKRB_BANK.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation1 (FormModeEnum.VM_SHOW));
        IKRB_BANK.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation1 (FormModeEnum.VM_EDIT));
        IKRB_BANK.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation1 (FormModeEnum.VM_DEL));

        doRefresh ();
    }        

    private void doRefresh ()
    {
        IKRB_RB.executeQuery ();
    }

    private void initToolBar ()
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE,
                                    ActionFactory.ActionTypeEnum.CREATE_BY, 
                                    ActionFactory.ActionTypeEnum.VIEW,
                                    ActionFactory.ActionTypeEnum.UPDATE,
                                    ActionFactory.ActionTypeEnum.DELETE);

        /**Справочник общих счетов*/
        toolBar.getItems().add(ActionFactory.createButton(new AllDictionaryAction((p) -> {
            if(dsIKRB_RB.getCurrentRow() == null)
                throw new AlertException("Не выбрана строка в верхней таблице");
            p.put(AllDictionaryAction.Params.IRBNUM, dsIKRB_RB.getCurrentRow().getIRBNUM());
        },null)));

        /**Справочник счетов в разрезе участников*/
        toolBar.getItems().add(ActionFactory.createButton(new AccEmployeeDictionaryAction((p) -> {
            if(dsIKRB_BANK.getCurrentRow() == null)
                throw  new AlertException("Не выбрана строка в нижней таблице");
            p.put(AccEmployeeDictionaryAction.Params.IBANKID, dsIKRB_BANK.getCurrentRow().getIBANKID());
        },null)));
        /**Настройка операций*/
        toolBar.getItems().add(ActionFactory.createButton(new OperationPropsAction((p) -> {
            if(dsIKRB_BANK.getCurrentRow() != null){
                p.put( OperationPropsAction.Params.IRB_NUM, dsIKRB_BANK.getCurrentRow() != null ? dsIKRB_BANK.getCurrentRow().getIRBNUM() : null);
                p.put(OperationPropsAction.Params.IBANK_ID, dsIKRB_BANK.getCurrentRow() != null ? dsIKRB_BANK.getCurrentRow().getIBANKID(): null);
            }
        },null)));

    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PIkrbRb p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbRb ();
                break;
            case VM_NONE:
                if (dsIKRB_RB.getCurrentRow () == null) 
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbRb ();
                for (IEntityProperty<PIkrbRb, ?> ep : EntityMetadataFactory.getEntityMetaData (PIkrbRb.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsIKRB_RB.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_RB.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<> (this, EditIkrbRb1Controller.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .doModal ();
    }
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbRb> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIKRB_RB.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIKRB_RB.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIKRB_RB.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IKRB_RB.requestFocus ();
    }
    private void doOperation1 ( JInvFXFormController.FormModeEnum mode )
    {
        PIkrbBank p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkrbBank ();
                if(dsIKRB_RB.getCurrentRow() == null)
                    throw new AlertException("Не установлена строка в вехней таблице");
                p.setIRBNUM(dsIKRB_RB.getCurrentRow().getIRBNUM());
                break;
            case VM_NONE:
                if (dsIKRB_BANK.getCurrentRow () == null)
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PIkrbBank ();
                p.setIRBNUM(dsIKRB_RB.getCurrentRow().getIRBNUM());
                for (IEntityProperty<PIkrbBank, ?> ep : EntityMetadataFactory.getEntityMetaData (PIkrbBank.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsIKRB_BANK.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIKRB_BANK.getCurrentRow ();
                break;
        }

        if (p != null)
            new FXFormLauncher<> (this, EditIkrbBankController.class)
                    .dataObject (p)
                    .dialogMode (mode)
                    .initProperties (getInitProperties ())
                    .callback (this::doFormResult1)
                    .doModal ();
    }
    private void doFormResult1 ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkrbBank> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ())
            {
                case VM_INS:
                    dsIKRB_BANK.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIKRB_BANK.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIKRB_BANK.removeCurrentRow ();
                    break;
                default:
                    break;
            }
        }

        IKRB_BANK.requestFocus ();
    }

}

