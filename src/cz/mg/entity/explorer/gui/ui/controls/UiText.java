package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public @Utility class UiText extends JTextField implements UiComponent {
    public UiText(@Mandatory String text) {
        this(text, FontStyle.PLAIN);
    }

    public UiText(@Mandatory String text, @Mandatory FontStyle fontStyle) {
        setEditable(false);
        setBorder(null);
        setBackground(null);
        setOpaque(false);
        setFont(new Font(getFont().getName(), fontStyle.getCode(), getFont().getSize()));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setSelectionStart(0);
                setSelectionEnd(0);
            }
        });
        setText(text);
    }

    public enum FontStyle {
        PLAIN,
        BOLD,
        ITALIC;

        public int getCode(){
            switch (this){
                case PLAIN: return Font.PLAIN;
                case BOLD: return Font.BOLD;
                case ITALIC: return Font.ITALIC;
                default: throw new UnsupportedOperationException();
            }
        }
    }
}
