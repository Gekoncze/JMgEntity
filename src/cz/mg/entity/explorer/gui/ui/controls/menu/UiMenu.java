package cz.mg.entity.explorer.gui.ui.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.ui.controls.UiComponent;

import javax.swing.*;


public @Utility class UiMenu extends JMenu implements UiComponent {
    public UiMenu(@Mandatory String name, @Optional Character mnemonic, @Mandatory UiMenuItem... items) {
        super(name);
        if(mnemonic != null) setMnemonic(mnemonic);
        for(UiMenuItem item : items){
            add(item);
        }
    }
}
