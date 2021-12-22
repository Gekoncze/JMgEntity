package cz.mg.entity.explorer.gui.ui.controls.field.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiFieldBase;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility interface UiFieldBaseFactory {
    @Mandatory UiFieldBase create(@Mandatory ExplorerWindow window);
}
