package cz.mg.entity.explorer.gui.ui.controls.field.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;

import java.awt.*;


public @Utility interface UiFieldImageProvider {
    @Optional Image get(@Optional Object value);
}
