package cz.mg.entity.explorer.gui.ui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import java.awt.*;


public @Utility class UiColorUtilities {
    public static @Mandatory Color grayscale(@Mandatory Color color){
        int lightness = (int)(color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
        if(lightness < 0) lightness = 0;
        if(lightness > 255) lightness = 255;
        return new Color(lightness, lightness, lightness);
    }

    public static @Mandatory Color copy(@Mandatory Color color){
        return new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
    }
}
