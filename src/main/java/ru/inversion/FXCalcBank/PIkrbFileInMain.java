package ru.inversion.FXCalcBank;

import java.util.Collections;
import java.util.Map;

import ru.inversion.FXCalcBank.controllers.ViewIkrbFileInController;
import ru.inversion.fx.app.property.PropertiesTypeEnum;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.tc.TaskContext;
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
        showViewIkrbFileIn (getPrimaryViewContext (), null, Collections.emptyMap ());
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
    public static void showViewIkrbFileIn ( ViewContext vc, TaskContext tc, Map<String, Object> map ) 
    {
        new FXFormLauncher<> (tc, vc, ViewIkrbFileInController.class)
            .initProperties (map)
            .show ();
    }
    
}

