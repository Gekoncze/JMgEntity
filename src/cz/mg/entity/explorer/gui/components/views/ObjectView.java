package cz.mg.entity.explorer.gui.components.views;

import cz.mg.annotations.classes.Utility;
import cz.mg.entity.explorer.gui.components.other.Refreshable;

import java.awt.event.KeyEvent;


public @Utility interface ObjectView extends Refreshable {
    Object getObject();
    void onKeyEvent(KeyEvent e);
}
