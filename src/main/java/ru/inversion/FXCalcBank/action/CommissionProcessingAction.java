package ru.inversion.FXCalcBank.action;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import ru.inversion.FXCalcBank.controllers.ViewIkrbPackController;
import ru.inversion.FXCalcBank.controllers.ViewIkrbSysparamController;
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

public class CommissionProcessingAction extends ActionBiComp<Map<CommissionProcessingAction.Params,Object>,Map<CommissionProcessingAction.Params,Object>> {

    public enum Params{callBack};

    private BiConsumer<AbstractBaseController.FormReturnEnum, JInvFXFormController<Object>> postCallback;

    public CommissionProcessingAction() {
        super(new IconDescriptorBuilder<>(FontAwesome.fa_bank, null).build(),
                BundleFXCalcBank.getString("TOOLTIP.COMMISSION.PROCESSING"),
                new HashMap<>());
        super.setHotKey(Arrays.asList(new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_ANY)));
        setDefaultHandler(this::defaultHandler);
    }

    public CommissionProcessingAction(Consumer<Map<Params,Object>> preCallback, BiConsumer<AbstractBaseController.FormReturnEnum,  JInvFXFormController<Object>> postCallback) {
        this();
        setActionPreCallback(preCallback);
        this.postCallback = postCallback;
    }

    private void defaultHandler(ActionEvent event) {
        new FXFormLauncher<>(tc, vc, ViewIkrbPackController.class)
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
        return  actObj.entrySet().stream().filter(p -> p.getValue() != null).collect(Collectors.toMap(i -> i.getKey().name(), i-> i.getValue()));
    }


}
