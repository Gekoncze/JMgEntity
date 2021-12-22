package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.event.FocusLostUserEventHandler;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility class UiBooleanFieldBase extends UiFieldBase {
    public UiBooleanFieldBase(@Mandatory ExplorerWindow window) {
        addFocusListener(new FocusLostUserEventHandler(window, this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        try {
            switch (getText().trim()){
                case "true": return true;
                case "false": return false;
                default: return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void setValue(@Optional Object value){
        if(value != null){
            if(value instanceof Boolean){
                setNull(false);
                setText(value.toString());
            } else {
                throw new IllegalArgumentException("Expected 'Boolean', but got '" + value.getClass().getSimpleName() + "'.");
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
