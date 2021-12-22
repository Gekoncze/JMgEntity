package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;

import java.awt.*;


public @Utility class UiImage extends UiPanel {
    private @Optional @Link Image image;

    public UiImage() {
        this(null);
    }

    public UiImage(@Optional Image image) {
        super(0, 0, UiAlignment.MIDDLE);
        this.image = image;
    }

    public @Optional Image getImage() {
        return image;
    }

    public void setImage(@Optional Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(@Mandatory Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
