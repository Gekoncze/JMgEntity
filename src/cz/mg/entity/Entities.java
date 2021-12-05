package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;


public @Utility class Entities {
    public static boolean isEntity(@Optional Object object){
        if(object != null){
            return object.getClass().isAssignableFrom(Entity.class);
        } else {
            return false;
        }
    }
}
