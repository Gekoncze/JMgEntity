package cz.mg.entity.explorer.gui.ui.enums;

import java.awt.*;


public enum UiFill {
    NONE,
    HORIZONTAL,
    VERTICAL,
    BOTH;

    public int getInternalCode() {
        switch (this) {
            case NONE:
                return GridBagConstraints.NONE;
            case HORIZONTAL:
                return GridBagConstraints.HORIZONTAL;
            case VERTICAL:
                return GridBagConstraints.VERTICAL;
            case BOTH:
                return GridBagConstraints.BOTH;
        }
        throw new IllegalStateException();
    }
}
