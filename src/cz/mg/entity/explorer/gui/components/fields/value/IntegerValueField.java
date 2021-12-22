package cz.mg.entity.explorer.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiIntegerFieldBase;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.fields.ValueField;


public @Utility class IntegerValueField extends ValueField {
    public IntegerValueField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(window, object, index, checkType(type), label, UiIntegerFieldBase::new);
    }

    private static @Mandatory Class checkType(@Mandatory Class type){
        if(type == Integer.class){
            return type;
        } else {
            throw new IllegalStateException();
        }
    }
}
