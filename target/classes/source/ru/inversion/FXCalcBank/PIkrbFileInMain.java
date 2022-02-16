package ru.inversion.FXCalcBank;

import java.util.Collections;
import java.util.Map;

import ru.inversion.FXCalcBank.controllers.ViewIkrbFileInController;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.annotation.StartMethod;

/**
 *
 * @author  XDWeloper
 * @since   Mon Feb 07 11:57:46 MSK 2022
 */
public class PIkrbFileInMain extends BaseApp 
{
    
    @Override
    protected void showMainWindow () 
    {
        showViewIkrbFileIn (getPrimaryViewContext (), Collections.emptyMap ());
    }

    @Override
    public String getAppID () 
    {
        return "XXI.PIKRBFILEIN"; 
    }
    
    public static void main (String[] args) 
    {
        launch (args);
    }

    @StartMethod (description = "Модуль \"Расчетный банк\"")
    private static void showViewIkrbFileIn(ViewContext vc, Map<String, Object> map)
    {
        new FXFormLauncher<> (null, vc, ViewIkrbFileInController.class)
            .initProperties (map)
            .show ();
    }
    
}

