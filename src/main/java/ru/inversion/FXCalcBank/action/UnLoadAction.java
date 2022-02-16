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

public class UnLoadAction extends ActionBiComp<Map<String, Object>, AbstractBaseController.FormReturnEnum> {

    private UnLoadAction() {
        super(new IconDescriptorBuilder<>(FontAwesome.fa_remove, null).build(),
                BundleFXCalcBank.getString("FILE.DELETE.TOOLTIP"),
                new HashMap<>());
        super.setHotKey(Arrays.asList(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY)));
        setDefaultHandler(this::defaultHandler);
        setParallel(true);
    }

    public UnLoadAction(Consumer<Map<String, Object>> preCallback, Consumer<AbstractBaseController.FormReturnEnum> postCallback) {
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

//public class ActionBuhosso extends ActionBiComp<Map<String,Object>,Map<String,Object>> {
//    private BiConsumer<AbstractBaseController.FormReturnEnum, Map<String,Object>> postCallback;
//
//    public ActionBuhosso() {
//        super(new IconDescriptorBuilder<>(FontAwesome.fa_table, null).build(),
//                BundleFxIntBuhAction.getString("TOOLTIP.BUHOSSO"),
//                new HashMap<>());
//        setDefaultHandler(this::defaultHandler);
//    }
//
//    public ActionBuhosso(Consumer<Map<String,Object>> preCallback, BiConsumer<AbstractBaseController.FormReturnEnum,
//            Map<String,Object>> postCallback) {
//        this();
//        setActionPreCallback(preCallback);
//        this.postCallback = postCallback;
//    }
//
//    private void defaultHandler(ActionEvent event) {
//        new FXFormLauncher<>(tc, vc, ViewBmosController.class)
//                .dialogMode(AbstractBaseController.FormModeEnum.VM_CHOICE)
//                .initProperties(actObj)
//                .callback( (ok, dctl) ->{
//                    if(postCallback!=null){
//                        postCallback.accept(ok, actObj);
//                    }
//                })
//                .modal(true)
//                .show();
//    }
//
//
//}
