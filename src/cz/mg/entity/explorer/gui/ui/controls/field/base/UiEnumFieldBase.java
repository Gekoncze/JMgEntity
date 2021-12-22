package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.explorer.gui.event.FocusLostUserEventHandler;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility class UiEnumFieldBase<T extends Enum> extends UiFieldBase {
    private final @Mandatory @Link Class<T> enumClass;

    public UiEnumFieldBase(@Mandatory ExplorerWindow window, @Mandatory Class<T> enumClass) {
        this.enumClass = enumClass;
        addFocusListener(new FocusLostUserEventHandler(window, this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        String text = getText();
        for(T value : enumClass.getEnumConstants()){
            if(value.name().equals(text)){
                return value;
            }
        }
        return null;
    }

    @Override
    public void setValue(@Optional Object value){
        if(value != null){
            if(enumClass.isInstance(value)){
                setNull(false);
                setText(((T)value).name());
            } else {
                throw new IllegalArgumentException("Expected '" + enumClass.getSimpleName() + "', but got '" + value.getClass().getSimpleName() + "'.");
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
