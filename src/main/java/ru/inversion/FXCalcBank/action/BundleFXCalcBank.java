package ru.inversion.FXCalcBank.action;

import ru.inversion.fx.form.AbstractBaseController;

import java.util.ResourceBundle;

public class BundleFXCalcBank {
        protected static final ResourceBundle BUNDLE= AbstractBaseController.getResourceBundle(BundleFXCalcBank.class);

        public static String getString(String key) {
            return BUNDLE.getString(key);
        }
    }


