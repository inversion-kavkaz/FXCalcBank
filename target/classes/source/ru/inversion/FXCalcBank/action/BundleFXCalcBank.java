package ru.inversion.FXCalcBank.action;

import ru.inversion.fx.form.AbstractBaseController;

import java.util.ResourceBundle;

class BundleFXCalcBank {
        private static final ResourceBundle BUNDLE= AbstractBaseController.getResourceBundle(BundleFXCalcBank.class);
        static String getString(String key) {
            return BUNDLE.getString(key);
        }
    }


