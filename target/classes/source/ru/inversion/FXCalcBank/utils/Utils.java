package ru.inversion.FXCalcBank.utils;

import javafx.util.Callback;
import ru.inversion.FXCalcBank.controllers.ViewIkrbFileInController;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataSetException;
import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.AbstractBaseController;
import ru.inversion.fx.form.controls.ITextFieldBase;
import ru.inversion.fx.form.lov.JInvEntityLov;

import java.io.IOException;

import static manifest.ManifestData.*;
import static ru.inversion.fx.app.es.JInvErrorService.logger;

public class Utils {

    public static String getTitleFromManifest() {
        String version = "";
        String buildDate = "";
        String buildNumber = "";

        try {
            loadDataFromManifestFile(ViewIkrbFileInController.class);
        } catch (IOException e) {
            logger.error("manifesrreader error " + e.getMessage());
        }
        if (isManifestDataLoad()) {
            version = getManifestData("Version");
            buildDate = getManifestData("Build-date");
            buildNumber = getManifestData("Build");
            version = version.substring(0, version.lastIndexOf(".") + 1).concat(buildNumber);
        }

        return "(Сборка:" + buildNumber + " от " + buildDate + ")";

    }

    /**
     * Пример:
     * JInvEntityLov<PMaskLov, Long> lovMask = setLov(this, null, PMaskLov.class, "CACMMASK", "", "", MASK, false, false, (param) -> {
     * PMaskLov item = (PMaskLov)param;
     * return item.getCACMMASK();
     * });
     * lovMask.bindControl(ACMNAME, PMaskLov::getCACMNAME);
     * lovMask.bindControl(IACMCODE_SA, PMaskLov::getIACMCODE);
     */
    public static JInvEntityLov setLov(AbstractBaseController obj
            , String title
            , Class lovClass
            , String valueColumnName
            , String wherePredicat
            , String orderBy
            , ITextFieldBase field
            , Boolean required
            , Boolean validateFromLov,
                                       Callback calback) {
        JInvEntityLov lov_field = new JInvEntityLov<>(lovClass, valueColumnName, calback);
        if (wherePredicat != null && !wherePredicat.isEmpty())
            lov_field.setWherePredicat(wherePredicat);
        lov_field.setSkipFilterString(true);
        if (orderBy != null && !orderBy.isEmpty())
            lov_field.setChoiceOrderBy(orderBy);
        lov_field.setTaskContext(obj.getTaskContext());
        if (title != null) lov_field.setTitle(title);
        field.setLOV(lov_field);
        field.setRequired(required);
        field.setValidateFromLOV(validateFromLov);

        return lov_field;
    }

}
