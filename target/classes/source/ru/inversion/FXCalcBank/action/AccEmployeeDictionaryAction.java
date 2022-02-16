package ru.inversion.FXCalcBank.action;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import ru.inversion.FXCalcBank.controllers.ViewIkrbBankAccController;
import ru.inversion.bicomp.action.ActionBiComp;
import ru.inversion.fx.form.AbstractBaseController;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.icons.IconDescriptorBuilder;
import ru.inversion.icons.enums.FontAwesome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Справочник счетов в разрезе участников
 */

public class AccEmployeeDictionaryAction extends ActionBiComp<Map<AccEmployeeDictionaryAction.Params,Object>,Map<AccEmployeeDictionaryAction.Params,Object>> {

    public enum Params{IBANKID}

    private BiConsumer<AbstractBaseController.FormReturnEnum, JInvFXFormController<Object>> postCallback;

    private AccEmployeeDictionaryAction() {
        super(new IconDescriptorBuilder<>(FontAwesome.fa_bandcamp, null).build(),
                BundleFXCalcBank.getString("TOOLTIP.ACC.EMPLOYEE"),
                new HashMap<>());
        super.setHotKey(Arrays.asList(new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN)));
        setDefaultHandler(this::defaultHandler);
    }

    public AccEmployeeDictionaryAction(Consumer<Map<Params,Object>> preCallback, BiConsumer<AbstractBaseController.FormReturnEnum,
            JInvFXFormController<Object>> postCallback) {
        this();
        setActionPreCallback(preCallback);
        this.postCallback = postCallback;
    }

    private void defaultHandler(ActionEvent event) {
        new FXFormLauncher<>(tc, vc, ViewIkrbBankAccController.class)
                .dialogMode(AbstractBaseController.FormModeEnum.VM_EDIT)
                .initProperties(initParams())
                .callback( (ok, dctl) ->{
                    if(postCallback!=null){
                        postCallback.accept(ok, dctl);
                    }
                })
                .modal(true)
                .show();
    }

    private Map<String, Object> initParams(){
        return  actObj.entrySet().stream().filter(p -> p.getValue() != null).collect(Collectors.toMap(i -> i.getKey().name(), Map.Entry::getValue));
    }


}
