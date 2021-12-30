package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityField;


public @Service class EntityClassNameFieldProvider {
    public @Optional EntityField get(@Optional EntityClass entityClass){
        if(entityClass != null){
            EntityField entityField = entityClass.getField("name");
            if (entityField != null) {
                if (entityField.getType().equals(String.class)) {
                    return entityField;
                }
            }
        }

        return null;
    }
}
