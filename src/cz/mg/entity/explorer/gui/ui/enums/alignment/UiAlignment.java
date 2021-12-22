package cz.mg.entity.explorer.gui.ui.enums.alignment;

import java.awt.*;


public enum UiAlignment {
    TOP_LEFT, TOP, TOP_RIGHT,
    LEFT, MIDDLE, RIGHT,
    BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;

    public UiHorizontalAlignment getHorizontal() {
        switch (this) {
            case TOP_LEFT:
                return UiHorizontalAlignment.LEFT;
            case LEFT:
                return UiHorizontalAlignment.LEFT;
            case BOTTOM_LEFT:
                return UiHorizontalAlignment.LEFT;

            case TOP:
                return UiHorizontalAlignment.MIDDLE;
            case MIDDLE:
                return UiHorizontalAlignment.MIDDLE;
            case BOTTOM:
                return UiHorizontalAlignment.MIDDLE;

            case TOP_RIGHT:
                return UiHorizontalAlignment.RIGHT;
            case RIGHT:
                return UiHorizontalAlignment.RIGHT;
            case BOTTOM_RIGHT:
                return UiHorizontalAlignment.RIGHT;
        }
        throw new IllegalStateException();
    }

    public UiVerticalAlignment getVertical() {
        switch (this) {
            case TOP_LEFT:
                return UiVerticalAlignment.TOP;
            case TOP:
                return UiVerticalAlignment.TOP;
            case TOP_RIGHT:
                return UiVerticalAlignment.TOP;

            case LEFT:
                return UiVerticalAlignment.MIDDLE;
            case MIDDLE:
                return UiVerticalAlignment.MIDDLE;
            case RIGHT:
                return UiVerticalAlignment.MIDDLE;

            case BOTTOM_LEFT:
                return UiVerticalAlignment.BOTTOM;
            case BOTTOM:
                return UiVerticalAlignment.BOTTOM;
            case BOTTOM_RIGHT:
                return UiVerticalAlignment.BOTTOM;
        }
        throw new IllegalStateException();
    }

    public int getInternalAnchor() {
        switch (this) {
            case TOP_LEFT:
                return GridBagConstraints.FIRST_LINE_START;
            case TOP:
                return GridBagConstraints.PAGE_START;
            case TOP_RIGHT:
                return GridBagConstraints.FIRST_LINE_END;

            case LEFT:
                return GridBagConstraints.LINE_START;
            case MIDDLE:
                return GridBagConstraints.CENTER;
            case RIGHT:
                return GridBagConstraints.LINE_END;

            case BOTTOM_LEFT:
                return GridBagConstraints.LAST_LINE_START;
            case BOTTOM:
                return GridBagConstraints.PAGE_END;
            case BOTTOM_RIGHT:
                return GridBagConstraints.LAST_LINE_END;
        }
        throw new IllegalStateException();
    }
}
