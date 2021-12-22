package cz.mg.entity.explorer.gui.ui.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.ui.controls.UiComponent;

import javax.swing.*;


public @Utility class UiMenuBar extends JMenuBar implements UiComponent {
    public UiMenuBar(@Mandatory UiMenu... menus) {
        for(UiMenu menu : menus){
            add(menu);
        }
    }
}
