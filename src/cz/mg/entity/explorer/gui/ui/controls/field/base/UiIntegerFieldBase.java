package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.event.FocusLostUserEventHandler;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility class UiIntegerFieldBase extends UiFieldBase {
    public UiIntegerFieldBase(@Mandatory ExplorerWindow window) {
        addFocusListener(new FocusLostUserEventHandler(window, this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        try {
            return Integer.parseInt(getText().trim().replace(" ", ""));
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void setValue(@Optional Object value){
        if(value != null){
            if(value instanceof Integer){
                setNull(false);
                setText(format((Integer)value));
            } else {
                throw new IllegalArgumentException("Expected 'Integer', but got '" + value.getClass().getSimpleName() + "'.");
            }
        } else {
            setNull(true);
            setText("");
        }
    }

    private @Mandatory String format(@Mandatory Integer value){
        String text = value.toString();
        StringBuilder formattedText = new StringBuilder();
        int ii = 0;
        for(int i = text.length() - 1; i >= 0; i--){
            char ch = text.charAt(i);
            formattedText.insert(0, ch);
            if((ii+1) % 3 == 0) formattedText.insert(0, ' ');
            ii++;
        }
        return formattedText.toString().replace("- ", "-").trim();
    }

    private void onFocusLost() {
        this.setValue(getValue());
    }
}
