package cz.mg.entity.explorer.gui.ui.dialogs;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;

import javax.swing.*;


public @Utility class UiInputDialog {
    private final @Mandatory @Value String title;
    private final @Mandatory @Value String message;

    public UiInputDialog(@Mandatory String title, @Mandatory String message) {
        this.title = title;
        this.message = message;
    }

    public @Optional String show(){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }
}
