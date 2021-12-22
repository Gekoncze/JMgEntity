package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.Gallery;

import java.awt.*;


public @Service class ObjectIconProvider {
    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();

    public @Optional Image get(@Mandatory Gallery gallery, @Optional Object object){
        if(object != null){
            return classIconProvider.get(gallery, object.getClass());
        } else {
            return null;
        }
    }
}
