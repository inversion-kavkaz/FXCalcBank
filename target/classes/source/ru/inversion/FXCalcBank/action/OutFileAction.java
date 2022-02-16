package ru.inversion.FXCalcBank.action;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import ru.inversion.bicomp.action.ActionBiComp;
import ru.inversion.fx.form.AbstractBaseController;
import ru.inversion.icons.IconDescriptorBuilder;
import ru.inversion.icons.enums.FontAwesome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OutFileAction extends ActionBiComp<Map<String, Object>, AbstractBaseController.FormReturnEnum> {

    private OutFileAction() {
        super(new IconDescriptorBuilder<>(FontAwesome.fa_file, null).build(),
                BundleFXCalcBank.getString("FILE.OUT.LOAD"),
                new HashMap<>());
        super.setHotKey(Arrays.asList(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY)));
        setDefaultHandler(this::defaultHandler);
        setParallel(true);
    }

    public OutFileAction(Consumer<Map<String, Object>> preCallback, Consumer<AbstractBaseController.FormReturnEnum> postCallback) {
        this();
        setActionPreCallback(preCallback);
        this.clbPost = postCallback;
    }

    private void defaultHandler(ActionEvent event) {
        if (clbPost != null) {
            clbPost.accept(AbstractBaseController.FormReturnEnum.RET_OK);
        }
    }
}

