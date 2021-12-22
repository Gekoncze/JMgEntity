package cz.mg.entity.explorer.gui.ui.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import java.awt.*;


public @Utility class UiValueMenuItem<T> extends UiMenuItem {
    public UiValueMenuItem(
        @Mandatory ExplorerWindow window,
        @Mandatory T value,
        @Mandatory String name,
        @Mandatory SelectEventHandler<T> handler
    ) {
        this(window, null, value, name, handler);
    }

    public UiValueMenuItem(
        @Mandatory ExplorerWindow window,
        @Optional Image image,
        @Mandatory T value,
        @Mandatory String name,
        @Mandatory SelectEventHandler<T> handler
    ) {
        super(window, image, name, () -> handler.select(value));
    }

    public interface SelectEventHandler<T> {
        void select(@Mandatory T value);
    }
}
