package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.event.ActionUserEventHandler;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import javax.swing.*;
import java.awt.*;


public @Utility class UiButton extends JButton implements UiComponent {
    public UiButton(
        @Mandatory ExplorerWindow window,
        @Optional Image icon,
        @Optional String text,
        @Optional String tooltip,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        addActionListener(new ActionUserEventHandler(window, handler));
        setBackground(new Color(0, 0, 0, 0));
        setBorder(null);
        setOpaque(false);

        if(icon != null){
            setIcon(new ImageIcon(icon));
        }

        if(text != null){
            setText(text);
        }

        if(tooltip != null){
            setToolTipText(tooltip);
        }
    }
}
