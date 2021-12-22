package cz.mg.entity.explorer.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.fields.ValueField;
import cz.mg.entity.explorer.gui.components.popups.EnumPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiEnumFieldBase;


public @Utility class EnumValueField extends ValueField {
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public EnumValueField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class<? extends Enum> type,
        @Mandatory String label
    ) {
        super(window, object, index, type, label, (w) -> new UiEnumFieldBase<>(w, type));
        this.popupMenu = new EnumPopupMenu(window, type, this::setValue);
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
