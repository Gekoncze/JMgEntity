package cz.mg.entity.explorer.gui.ui.controls.field;

import cz.mg.annotations.classes.Utility;
import cz.mg.entity.explorer.gui.ui.UiConstants;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.gui.ui.controls.UiPanel;

import javax.swing.*;


public abstract @Utility class UiField extends UiPanel {
    protected static final int BORDER = 2;
    private static final int PADDING = 2;

    public UiField() {
        super(BORDER, PADDING, UiAlignment.TOP);
        setOpaque(true);
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(UiConstants.getListBackgroundColor());
    }
}
