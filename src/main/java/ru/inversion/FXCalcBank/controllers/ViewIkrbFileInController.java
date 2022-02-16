package ru.inversion.FXCalcBank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ru.inversion.FXCalcBank.action.DownloadFileAction;
import ru.inversion.FXCalcBank.action.OutFileAction;
import ru.inversion.FXCalcBank.action.RunTransactionAction;
import ru.inversion.FXCalcBank.action.UnLoadAction;
import ru.inversion.FXCalcBank.pojo.PIkrbFileIn;
import ru.inversion.FXCalcBank.pojo.PIkrbFileRows;
import ru.inversion.FXCalcBank.utils.Utils;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.ActionFactory;
import ru.inversion.fx.form.Alerts;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXBrowserController;
import ru.inversion.fx.form.action.JInvAction;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.fxplrefer.file.ViewVPlFileController;
import ru.inversion.utils.ConnectionStringFormatEnum;

import java.io.File;
import java.time.LocalDate;

/**
 * @author XDWeloper
 * @since Mon Feb 07 11:57:32 MSK 2022
 */
@SuppressWarnings("ALL")
public class ViewIkrbFileInController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbFileIn> IKRB_FILE_IN;
    @FXML
    private JInvTable<PIkrbFileRows> IKRB_FILE_ROWS;
    @FXML
    private JInvToolBar toolBar;

    private String lastPath;

    private final XXIDataSet<PIkrbFileIn> dsIKRB_FILE_IN = new XXIDataSet<>(getTaskContext(), PIkrbFileIn.class);
    private final XXIDataSet<PIkrbFileRows> dsIKRB_FILE_ROWS = new XXIDataSet<>(getTaskContext(), PIkrbFileRows.class);

    {
        DataLinkBuilder.linkDataSet(dsIKRB_FILE_IN, dsIKRB_FILE_ROWS, PIkrbFileIn::getIFILEINID, "IFILEINID");
        String dateFilter = "DFILEINDATE = to_date('" + LocalDate.now() + "', 'yyyy-MM-dd')";
        dsIKRB_FILE_IN.setFilter(dateFilter, false,false);
    }

    @Override
    protected void init() throws Exception {

        setTitle(getBundleString("VIEW.TITLE") + "   " + getTaskContext().getConnectionString(ConnectionStringFormatEnum.SQL_SIMPLE) + "   " + Utils.getTitleFromManifest());

        DSFXAdapter<PIkrbFileIn> dsFxIn = DSFXAdapter.bind(dsIKRB_FILE_IN, IKRB_FILE_IN, null, false);
        DSFXAdapter<PIkrbFileRows> dsFxRowsa = DSFXAdapter.bind(dsIKRB_FILE_ROWS, IKRB_FILE_ROWS, null, false);
        dsFxIn.setEnableFilter(true);
        dsFxRowsa.setEnableFilter(true);
        initToolBar();
        IKRB_FILE_IN.setToolBar(toolBar);
        IKRB_FILE_IN.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());
        toolBar.getItems().add(ActionFactory.createButton(new DownloadFileAction(null, (a) -> {loadFile();doRefresh();})));
        UnLoadAction unLoadAction = new UnLoadAction(null, (a) -> deleteFile());
        unLoadAction.setFormStateListener(this);
        toolBar.getItems().add(ActionFactory.createButton(unLoadAction));
        OutFileAction outFile = new OutFileAction(null, (a) -> uploadFile());
        outFile.setFormStateListener(this);
        toolBar.getItems().add(ActionFactory.createButton(outFile));
        RunTransactionAction runTransactionAction = new RunTransactionAction(null, (a) -> runTransaction());
        runTransactionAction.setFormStateListener(this);
        toolBar.getItems().add(ActionFactory.createButton(runTransactionAction));

        doRefresh();
    }

    private void uploadFile() {
        if(dsIKRB_FILE_IN.getCurrentRow() == null) return;
        new ParamMap().add("IFileInID",dsIKRB_FILE_IN.getCurrentRow().getIFILEINID()).execParallel(this, "upLoadFile", null);
    }

    private void runTransaction() {
        if(dsIKRB_FILE_IN.getCurrentRow() == null) return;
        new ParamMap().add("IFileInID",dsIKRB_FILE_IN.getCurrentRow().getIFILEINID()).execParallel(this, "runTransaction", null);
    }

    private void deleteFile() {
        if (dsIKRB_FILE_IN.getCurrentRow() == null) {
            Alerts.error(this, getBundleString("ERROR.NOT.OBJECT"));
            return;
        }
        if (!Alerts.yesNo(this, getBundleString("DELETE"), "", getBundleString("FILE.DELETE.YES/NO", "\"Тут название файла\"", " тут дата")))
            ;
        return;
    }

    @FXML
    private void loadFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(getBundleString("SCR_FILE.TITLE"));
        if (lastPath != null)
            fileChooser.setInitialDirectory(new File(lastPath));
        File file = fileChooser.showOpenDialog(this.getViewContext().getStage());
        if (file == null) return;
        lastPath = file.getParent();

        if (Alerts.yesNo(this, getBundleString("LOAD")
                , getBundleString("LOAD.INFO1")
                , getBundleString("LOAD.INFO2", file.getName())
                , null)) {
            JInvAction a = new JInvAction();
            a.setHandler(event -> {
                try {
                    new ParamMap()
                            .add("lFile", file)
                            .add("fileName", file.getName())
                            .exec(this, "fileSave");
                } catch (SQLExpressionException e) {
                    new AlertException(e.getMessage());
                }
            });
            a.setParallel(true);
            a.setFormStateListener(this);
            a.handle ();
        }
    }

    private void doRefresh() {
        IKRB_FILE_IN.executeQuery();
    }

    private void initToolBar() {
        JInvButtonPrint bp = new JInvButtonPrint(this::setPrintParam);
        bp.setReportTypeId(200000);
        toolBar.getItems().add(bp);

        toolBar.setStandartActions(
                ActionFactory.ActionTypeEnum.REFRESH
        );
    }

    private void setPrintParam(ApReport ap) {
        if (dsIKRB_FILE_IN.isEmpty())
            throw new StopExecuteActionBiCompException();
    }

    /**
     * Справочник типов счетов
     */
    @FXML
    private void openAccTypeDictionary() {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbTypeaccController.class)
                .initProperties(getInitProperties())
                .show();

    }

    /**
     * справочник тарифов
     */
    @FXML
    private void openTarScaleDictionary() {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbTarScaleController.class)
                .initProperties(getInitProperties())
                .show();
    }

    /**
     * справочник действий
     */
    @FXML
    private void openRBDictionary() {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbRbController.class)
                .initProperties(getInitProperties())
                .show();
    }

    /**справочник участников*/
    public void openBankDictionary(ActionEvent actionEvent) {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbRb1Controller.class)
                .initProperties(getInitProperties())
                .show();
    }

    //Форматы файлов обмена
    public void openVPlFile(ActionEvent actionEvent) {
        new FXFormLauncher<> (taskContext, viewContext, ViewVPlFileController.class)
                .initProperties (getInitProperties())
                .show ();
    }
}

