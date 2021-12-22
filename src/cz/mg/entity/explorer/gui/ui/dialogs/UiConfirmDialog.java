package cz.mg.entity.explorer.gui.ui.dialogs;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;

import javax.swing.*;


public @Utility class UiConfirmDialog {
    private final @Mandatory @Value String title;
    private final @Mandatory @Value String message;

    public UiConfirmDialog(@Mandatory String title, @Mandatory String message) {
        this.title = title;
        this.message = message;
    }

    public @Mandatory Choice show(){
        int choice = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
        if(choice == JOptionPane.YES_OPTION) return Choice.YES;
        if(choice == JOptionPane.NO_OPTION) return Choice.NO;
        return Choice.CANCEL;
    }

    public enum Choice {
        YES,
        NO,
        CANCEL
    }
}
