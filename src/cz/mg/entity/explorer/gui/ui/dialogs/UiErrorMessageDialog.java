package cz.mg.entity.explorer.gui.ui.dialogs;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;

import javax.swing.*;


public @Utility class UiErrorMessageDialog {
    private final @Mandatory @Value String title;
    private final @Mandatory @Value String message;

    public UiErrorMessageDialog(@Mandatory Exception exception){
        this(exception.getClass().getSimpleName(), getMessage(exception));
        exception.printStackTrace();
    }

    public UiErrorMessageDialog(@Mandatory String title, @Mandatory String message) {
        this.title = title;
        this.message = message;
    }

    public void show(){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private static @Mandatory String getMessage(@Mandatory Exception e){
        if(e.getMessage() != null){
            return e.getMessage();
        } else {
            return e.getClass().getSimpleName();
        }
    }
}
