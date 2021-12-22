package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.explorer.gui.Gallery;

import java.awt.*;


public @Service class ClassIconProvider {
    public @Optional Image get(@Mandatory Gallery gallery, @Optional Class clazz){
        if(clazz != null){
            return gallery.getImage(getIconName(clazz));
        } else {
            return null;
        }
    }

    private @Mandatory String getIconName(@Mandatory Class clazz){
        return clazz.getSimpleName().toLowerCase() + ".png";
    }
}
