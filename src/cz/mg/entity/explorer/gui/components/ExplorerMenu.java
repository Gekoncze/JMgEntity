package cz.mg.entity.explorer.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiMenu;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiMenuBar;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiMenuItem;

import javax.swing.filechooser.FileFilter;

import static cz.mg.entity.explorer.gui.ui.enums.UiKey.*;
import static cz.mg.entity.explorer.gui.ui.enums.UiKeyModifier.CTRL;
import static cz.mg.entity.explorer.gui.ui.enums.UiKeyModifier.SHIFT;


public @Utility class ExplorerMenu extends UiMenuBar {
    private final @Mandatory @Part ExplorerActions actions;

    public ExplorerMenu(@Mandatory ExplorerWindow window, @Mandatory FileFilter projectFileFilter) {
        actions = new ExplorerActions(window, projectFileFilter);

        add(new UiMenu(
            "File", 'F',

            new UiMenuItem(window, null, "New project", 'N', N, CTRL, actions::newProject),
            new UiMenuItem(window, null, "Open project", 'O', O, CTRL, actions::openProject),
            new UiMenuItem(window, null, "Save project", 'S', S, CTRL, actions::saveProject),
            new UiMenuItem(window, null, "Save project as", 'A', S, CTRL | SHIFT, actions::saveProjectAs),
            new UiMenuItem(window, null, "Close project", 'C', null, null, actions::closeProject),
            new UiMenuItem(window, null, "Exit", 'E', null, null, actions::exit)
        ));

        add(new UiMenu(
            "Edit", 'E',

            new UiMenuItem(window, null, "Undo", 'U', Z, CTRL, actions::undo),
            new UiMenuItem(window, null, "Redo", 'E', Z, CTRL | SHIFT, actions::redo)
        ));

        add(new UiMenu(
            "View", 'V',

            new UiMenuItem(window, null, "Refresh", null, F5, 0, actions::refresh),
            new UiMenuItem(window, null, "Close active tab", null, W, CTRL, actions::closeActiveTab),
            new UiMenuItem(window, null, "Next Tab", null, TAB, CTRL, actions::selectNextTab),
            new UiMenuItem(window, null, "Previous Tab", null, TAB, CTRL | SHIFT, actions::selectPreviousTab)
        ));
    }

    public @Mandatory ExplorerActions getActions() {
        return actions;
    }
}
