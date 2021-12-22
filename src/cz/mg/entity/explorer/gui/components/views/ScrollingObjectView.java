package cz.mg.entity.explorer.gui.components.views;

import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class ScrollingObjectView extends JScrollPane implements ObjectView {
    private final ObjectView view;

    public ScrollingObjectView(@Mandatory ObjectView view) {
        super((Component) view);
        this.view = view;
    }

    @Override
    public Object getObject() {
        return view.getObject();
    }

    @Override
    public void onKeyEvent(KeyEvent e) {
        view.onKeyEvent(e);
    }

    @Override
    public void refresh() {
        view.refresh();
    }
}
