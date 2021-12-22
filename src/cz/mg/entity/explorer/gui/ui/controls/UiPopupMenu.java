package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiMenuItem;

import javax.swing.*;
import java.awt.*;


public @Utility class UiPopupMenu extends JPopupMenu implements UiComponent {
    public UiPopupMenu(@Mandatory UiMenuItem... items) {
        for(UiMenuItem item : items){
            add(item);
        }
    }

    public UiPopupMenu(@Mandatory Iterable<? extends UiMenuItem> items) {
        for(UiMenuItem item : items){
            add(item);
        }
    }

    public UiMenuItem getMenuItem(int i) {
        return (UiMenuItem) getComponent(i);
    }

    public void show(@Mandatory Component component) {
        show(component, 0, component.getHeight());
    }
}
