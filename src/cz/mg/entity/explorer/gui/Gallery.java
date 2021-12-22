package cz.mg.entity.explorer.gui;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.map.Map;

import java.awt.*;


public class Gallery {
    private final @Mandatory Map<String, Image> images = new Map<>();

    public Gallery() {
    }

    public @Optional Image getImage(@Mandatory String name){
        return images.get(name);
    }

    public void setImage(@Mandatory String name, @Optional Image image){
        images.set(name, image);
    }
}
