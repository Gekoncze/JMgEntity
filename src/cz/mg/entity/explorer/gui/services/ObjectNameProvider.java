package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;


public @Service class ObjectNameProvider {
    private final @Mandatory @Shared EntityNameProvider entityNameProvider = new EntityNameProvider();

    public @Mandatory String get(@Optional Object object){
        String name = entityNameProvider.get(object);
        if(name != null && name.trim().length() > 0){
            return name;
        } else if(object != null) {
            return object.getClass().getSimpleName();
        } else {
            return "";
        }
    }
}
