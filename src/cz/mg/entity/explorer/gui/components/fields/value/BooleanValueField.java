package cz.mg.entity.explorer.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiBooleanFieldBase;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.fields.ValueField;
import cz.mg.entity.explorer.gui.components.popups.BooleanPopupMenu;


public @Utility class BooleanValueField extends ValueField {
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public BooleanValueField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(window, object, index, checkType(type), label, UiBooleanFieldBase::new);
        this.popupMenu = new BooleanPopupMenu(window, this::setValue);
    }

    private static @Mandatory Class checkType(@Mandatory Class type){
        if(type == Boolean.class){
            return type;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    protected void onEditButtonClicked() {
        super.onEditButtonClicked();
        popupMenu.show(field.getBase());
    }

    @Override
    protected void onFocusLost() {
        if(!popupMenu.isVisible()){
            super.onFocusLost();
        }
    }
}
