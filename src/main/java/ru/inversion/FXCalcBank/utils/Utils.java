package ru.inversion.FXCalcBank.utils;

import ru.inversion.FXCalcBank.controllers.ViewIkrbFileInController;

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
}
