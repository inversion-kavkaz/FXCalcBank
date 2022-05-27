package ru.inversion.FXCalcBank.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ru.inversion.FXCalcBank.action.*;
import ru.inversion.FXCalcBank.pojo.PIkrbFileIn;
import ru.inversion.FXCalcBank.pojo.PIkrbFileRows;
import ru.inversion.FXCalcBank.pojo.PIkrbSysparam;
import ru.inversion.FXCalcBank.pojo.PIkrbTmpFile;
import ru.inversion.FXCalcBank.utils.Utils;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.SQLDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.app.AlertException;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.action.JInvAction;
import ru.inversion.fx.form.controls.JInvTable;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.fxplrefer.file.ViewVPlFileController;
import ru.inversion.utils.ConnectionStringFormatEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

/**
 * @author XDWeloper
 * @since Mon Feb 07 11:57:32 MSK 2022
 */
public class ViewIkrbFileInController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkrbFileIn> IKRB_FILE_IN;
    @FXML
    private JInvTable<PIkrbFileRows> IKRB_FILE_ROWS;
    @FXML
    private JInvToolBar toolBar;

    private String lastPath;
    private Boolean isTransactionYes = false;

    private final XXIDataSet<PIkrbFileIn> dsIKRB_FILE_IN = new XXIDataSet<>(getTaskContext(), PIkrbFileIn.class);
    private final XXIDataSet<PIkrbFileRows> dsIKRB_FILE_ROWS = new XXIDataSet<>(getTaskContext(), PIkrbFileRows.class);

    {
        DataLinkBuilder.linkDataSet(dsIKRB_FILE_IN, dsIKRB_FILE_ROWS, PIkrbFileIn::getIFILEINID, "IFILEINID");
        String dateFilter = " IFILEINSTATUS <> 1";
        //String dateFilter = "trunc(DFILEINDATE) = to_date('" + LocalDate.now() + "', 'yyyy-MM-dd')";
        dsIKRB_FILE_IN.setFilter(dateFilter, false, false);
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
        toolBar.getItems().add(ActionFactory.createButton(new DownloadFile(null, (a) -> {loadFile();})));
        toolBar.getItems().add(ActionFactory.createButton(new UnLoad(null, (a) -> {deleteFile();})));
        toolBar.getItems().add(ActionFactory.createButton(new OutFile(null, (a) -> uploadFile(null))));
        toolBar.getItems().add(ActionFactory.createButton(new RunTransaction((a) -> CheckDate(), (a) -> runTransaction())));
        toolBar.getItems().add(ActionFactory.createButton(new CommissionProcessingAction((c) -> {
            c.put(CommissionProcessingAction.Params.callBack,this);
        }, null)));
        toolBar.getItems().add(ActionFactory.createButton(new PropAction()));

        doRefresh();
    }

    private void CheckDate() {
        ParamMap p;
        try {
            p = new ParamMap().exec(this,"GetTrnDate");
            LocalDate date = p.getLocalDate("date");
            isTransactionYes = Alerts.yesNo(this,"Дата проводки", "Сделать проводки на " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
    }

    void uploadFile(Long IFileInID) {
        if(IFileInID == null) {
            if (dsIKRB_FILE_IN.getCurrentRow() == null) return;
                IFileInID = dsIKRB_FILE_IN.getCurrentRow().getIFILEINID();
        }

        Long finalIFileInID = IFileInID;
        runBlockingProc(() -> {
            Long result = 1L;
            ParamMap p;
            try {
                p = new ParamMap().add("IFileInID", finalIFileInID).exec(this, "upLoadFile");
                result = p.getLong("result");
                if (result < 1)
                    throw new SQLExpressionException();
            } catch (SQLExpressionException e) {
                throw new AlertException("Ошибка выгрузки.");
            }

            try {
                uploadFiles();
            } catch (DataSetException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void runTransaction() {
        //if (dsIKRB_FILE_IN.getCurrentRow() == null) return;
        if (!isTransactionYes) return;

        runBlockingProc(() -> {
            Long result = 0L;
            ParamMap p;
            try {
                //p = new ParamMap().add("IFileInID", dsIKRB_FILE_IN.getCurrentRow().getIFILEINID()).exec(this, "runTransaction");
                p = new ParamMap().exec(this, "runTransaction");
                result = p.getLong("result");
                if (result < 0)
                    throw new SQLExpressionException();
            } catch (SQLExpressionException e) {
                throw new AlertException("Ошибка выполнения транзакции.");
            }
            Platform.runLater(() -> {
                //Alerts.info(this, "ИНФОРМАЦИЯ", "Проводки выполнены успешно", "Файл: " + dsIKRB_FILE_IN.getCurrentRow().getCFILEINNAME());
                new FXFormLauncher<> (this, ViewPlPltController.class)
                .callback (this::doTrnResult)
                .doModal ();
                doRefresh();
            });
            return null;
        });
    }

    private void doTrnResult(FormReturnEnum formReturnEnum, JInvFXFormController<Object> objectJInvFXFormController) {
        try {
            new ParamMap().exec(this,formReturnEnum.equals(FormReturnEnum.RET_OK) ? "plt2trnMain" : "recall_plt_d");
            if(formReturnEnum.equals(FormReturnEnum.RET_OK))
                Alerts.info(this, "ИНФОРМАЦИЯ", "Проводки выполнены успешно");
        } catch (SQLExpressionException e) {
            throw new AlertException(e);
        }
    }

    private void deleteFile() {
        if (dsIKRB_FILE_IN.getCurrentRow() == null)
            throw new AlertException(getBundleString("ERROR.NOT.OBJECT"));
        if (!Alerts.yesNo(this, getBundleString("DELETE"), "",
                getBundleString("FILE.DELETE.YES/NO",
                        dsIKRB_FILE_IN.getCurrentRow().getCFILEINNAME(),
                        dsIKRB_FILE_IN.getCurrentRow().getDFILEINDATE())))
            return;

        runBlockingProc(() -> {
            ParamMap p;
            try {
                p = new ParamMap().add("IFileInID", dsIKRB_FILE_IN.getCurrentRow().getIFILEINID()).exec(this, "delFile");
                Long result = p.getLong("result");
                if (result != 1)
                    throw new SQLExpressionException();
                doRefresh();
            } catch (SQLExpressionException e) {
                throw new AlertException("Ошибка удаления");
            }
            return null;
        });
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

            runBlockingProc(() -> {
                try {
                    new ParamMap()
                            .add("lFile", file)
                            .add("fileName", file.getName())
                            .exec(this, "fileSave");
                    doRefresh();
                } catch (SQLExpressionException e) {
                    throw new AlertException(e.getMessage());
                }
                return null;
            });
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
    /**
     * справочник бинов
     */
    @FXML
    private void BINDictionary() {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbGrbinController.class)
                .initProperties(getInitProperties())
                .show();
    }

    /**
     * справочник участников
     */
    public void openBankDictionary(ActionEvent actionEvent) {
        new FXFormLauncher<>(taskContext, viewContext, ViewIkrbRb1Controller.class)
                .initProperties(getInitProperties())
                .show();
    }

    /**
     * Форматы файлов обмена
     */
    public void openVPlFile(ActionEvent actionEvent) {
        new FXFormLauncher<>(taskContext, viewContext, ViewVPlFileController.class)
                .initProperties(getInitProperties())
                .show();
    }

    private void runBlockingProc(Supplier supl) {
        JInvAction a = new JInvAction();
        a.setHandler(event -> {
            supl.get();
        });
        a.setParallel(true);
        a.setFormStateListener(this);
        a.handle();
    }

    void uploadFiles() throws DataSetException {
        /**Получить каталог выгрузки*/
        SQLDataSet<PIkrbSysparam> catalogName = populateDataSet(PIkrbSysparam.class, "select crbsysvalue from ikrb_sysparam", " irbsysid = 1", null, 1);
        if (catalogName.getRows().size() < 1)
            throw new AlertException("Не найдена настройка пути выгрузки файлов");
        String outFileDir = catalogName.getRow(0).getCRBSYSVALUE();

        SQLDataSet<PIkrbTmpFile> tmp_file_names = populateDataSet(PIkrbTmpFile.class, "select fname from ikrb_tmp_file ", null, null, 1);
        tmp_file_names.getRows().forEach(t -> {
            logger.info("пишем файлик:" + t.getFNAME());
            save(outFileDir, t.getFNAME());
        });
        Platform.runLater(() -> {
            Alerts.info(this, "ИНФОРМАЦИЯ", "Выгрузка файлов", "Выгружено " + tmp_file_names.getRows().size() + " \n каталог выгрузки: " + outFileDir);
        });
    }

    private void save(String outFileDir, String newFileName) {
        InputStream inputStream = null;

        try {
            inputStream = getFile(newFileName).getBinaryStream();
        } catch (SQLException e) {
            logger.error(String.format("Error read file stream from data base"));
            return;
        }

        File fileOut = new File(outFileDir.concat(File.separator).concat(newFileName));
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileOut);
            for (int c = inputStream.read(); c != -1; c = inputStream.read()) {
                fileOutputStream.write(c);
            }

        } catch (IOException e) {
            logger.error(String.format("Error for get file out stream"));
            return;
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Blob getFile(String fName) {
        ParamMap p;
        try {
            p = new ParamMap().add("FName", fName).exec(this, "GetFile");
        } catch (SQLExpressionException ex) {
            logger.error(ex.getContentText());
            return null;
        }
        return (Blob) p.getParam("result");
    }
}

