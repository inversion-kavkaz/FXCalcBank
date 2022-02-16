package ru.inversion.FXCalcBank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.inversion.FXCalcBank.pojo.PIkrbRb;
import ru.inversion.FXCalcBank.pojo.PIkrbTypeev;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
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
 * @since   Thu Feb 10 16:03:27 MSK 2022
 */
public class ViewIkrbRbController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkrbRb> IKRB_RB;
    @FXML private JInvTable<PIkrbTypeev> IKRB_TYPEEV;

    private final XXIDataSet<PIkrbRb> dsIKRB_RB = new XXIDataSet<> (getTaskContext(),PIkrbRb.class);
    private final XXIDataSet<PIkrbTypeev> dsIKRB_TYPEEV = new XXIDataSet<> (getTaskContext(),PIkrbTypeev.class);

    {
        DataLinkBuilder.linkDataSet(dsIKRB_RB,dsIKRB_TYPEEV,PIkrbRb::getIRBNUM, "IRBNUM");
    }


    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        DSFXAdapter<PIkrbRb> dsfx = DSFXAdapter.bind (dsIKRB_RB, IKRB_RB, null, false);
        DSFXAdapter<PIkrbTypeev> dsfx1 = DSFXAdapter.bind (dsIKRB_TYPEEV, IKRB_TYPEEV, null, false);
        dsfx.setEnableFilter (true);
        dsfx1.setEnableFilter (true);
        doRefresh ();
    }        
    private void doRefresh ()
    {
        IKRB_RB.executeQuery ();
    }
}

