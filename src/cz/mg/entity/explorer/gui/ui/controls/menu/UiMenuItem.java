package cz.mg.entity.explorer.gui.ui.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.event.ActionUserEventHandler;
import cz.mg.entity.explorer.gui.ui.controls.UiComponent;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import javax.swing.*;
import java.awt.*;


public @Utility class UiMenuItem extends JMenuItem implements UiComponent {
    public UiMenuItem(
        @Mandatory ExplorerWindow window,
        @Optional Image image,
        @Mandatory String name,
        @Optional Character mnemonic,
        @Optional Integer code,
        @Optional Integer modifiers,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        super(name);
        if(image != null) setIcon(new ImageIcon(image));
        if(mnemonic != null) setMnemonic(mnemonic);
        if(code != null && modifiers != null) setAccelerator(KeyStroke.getKeyStroke(code, modifiers));
        addActionListener(new ActionUserEventHandler(window, handler));
    }

    public UiMenuItem(
        @Mandatory ExplorerWindow window,
        @Optional Image image,
        @Mandatory String name,
        @Mandatory ActionUserEventHandler.Handler handler
    ){
        this(window, image, name, null, null, null, handler);
    }
}
