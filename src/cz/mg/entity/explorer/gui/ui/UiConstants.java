package cz.mg.entity.explorer.gui.ui;

import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;
import java.awt.*;


public class UiConstants {
    public static @Mandatory Color getListBackgroundColor(){
        return UiColorUtilities.copy(UIManager.getDefaults().getColor("List.background"));
    }

    public static @Mandatory Color getListSelectionBackgroundColor(){
        return UiColorUtilities.copy(UIManager.getDefaults().getColor("List.selectionBackground"));
    }

    public static @Mandatory Color getTextFieldBackgroundColor(){
        return UiColorUtilities.copy(UIManager.getDefaults().getColor("TextField.background"));
    }
}
