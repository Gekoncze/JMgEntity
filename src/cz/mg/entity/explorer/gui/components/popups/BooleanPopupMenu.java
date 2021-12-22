package cz.mg.entity.explorer.gui.components.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import static cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class BooleanPopupMenu extends UiPopupMenu {
    public BooleanPopupMenu(@Mandatory ExplorerWindow window, @Mandatory SelectEventHandler<Boolean> handler) {
        super(
            new UiValueMenuItem<>(window, true, "true", handler),
            new UiValueMenuItem<>(window, false, "false", handler)
        );
    }
}
