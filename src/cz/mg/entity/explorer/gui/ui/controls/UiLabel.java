package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;

import java.awt.*;
import java.awt.event.MouseListener;

import static cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment.LEFT;
import static cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment.MIDDLE;
import static cz.mg.entity.explorer.gui.ui.enums.UiFill.BOTH;
import static cz.mg.entity.explorer.gui.ui.enums.UiFill.NONE;


public @Utility class UiLabel extends UiPanel implements UiComponent {
    private static final int PADDING = 4;

    private final @Mandatory @Shared UiImage imageComponent;
    private final @Mandatory @Shared UiText textComponent;

    public UiLabel(@Mandatory Image icon, @Mandatory String text) {
        super(0, PADDING, LEFT);

        imageComponent = new UiImage(icon);
        add(imageComponent, 0, 0, 0, 0, MIDDLE, BOTH);

        textComponent = new UiText(text, UiText.FontStyle.BOLD);
        add(textComponent, 1, 0, 0, 0, MIDDLE, NONE);

        imageComponent.setPreferredSize(new Dimension(icon.getWidth(this), icon.getHeight(this)));

        rebuild();
    }

    @Override
    public void addMouseListener(@Mandatory MouseListener listener) {
        super.addMouseListener(listener);
        imageComponent.addMouseListener(listener);
        textComponent.addMouseListener(listener);
    }
}
