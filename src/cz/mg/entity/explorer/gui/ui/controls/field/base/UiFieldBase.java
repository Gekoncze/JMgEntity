package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.explorer.gui.ui.controls.UiComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public abstract @Utility class UiFieldBase extends JTextField implements UiComponent {
    private static final @Mandatory @Shared Color GREY_COLOR = new Color(160, 160, 160);
    private @Value boolean isNull = false;
    private @Value boolean locked = false;

    public UiFieldBase() {
        setOpaque(false);
        setBorder(null);
        setBackground(null);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                repaint();
            }
        });
        lock();
    }

    protected boolean isNull() {
        return isNull;
    }

    protected void setNull(boolean value) {
        isNull = value;
        repaint();
    }

    public void lock(){
        locked = true;
        setEditable(false);
        getCaret().setVisible(false);
    }

    public void unlock(){
        locked = false;
        setEditable(true);
        getCaret().setVisible(true);
        requestFocus();
    }

    public boolean isLocked() {
        return locked;
    }

    public abstract @Optional Object getValue();
    public abstract void setValue(@Optional Object value);

    @Override
    protected void paintComponent(@Mandatory Graphics g) {
        super.paintComponent(g);
        if(isNull && getText().length() <= 0){
            if(!hasFocus() || !isEditable()){
                g.setColor(GREY_COLOR);
                g.setFont(getFont());
                g.drawString(
                    "null",
                    getInsets().left,
                    getHeight() / 2 + g.getFontMetrics().getAscent() / 2 - 2
                );
            }
        }
    }
}
