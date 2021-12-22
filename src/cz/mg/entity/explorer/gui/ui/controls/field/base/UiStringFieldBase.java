package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.event.FocusLostUserEventHandler;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility class UiStringFieldBase extends UiFieldBase {
    public UiStringFieldBase(@Mandatory ExplorerWindow window) {
        addFocusListener(new FocusLostUserEventHandler(window, this::onFocusLost));
    }

    @Override
    public @Optional Object getValue() {
        return getText();
    }

    @Override
    public void setValue(@Optional Object value) {
        if(value != null){
            if(value instanceof String){
                setNull(false);
                setText((String)value);
            } else {
                throw new IllegalArgumentException("Expected 'String', but got '" + value.getClass().getSimpleName() + "'.");
            }
        } else {
            setNull(true);
            setText("");
        }
    }

    private void onFocusLost() {
        setValue(getValue());
    }
}
