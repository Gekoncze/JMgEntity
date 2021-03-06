package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;


public @Service class EntityNameProvider {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared EntityClassNameFieldProvider entityClassNameFieldProvider = new EntityClassNameFieldProvider();

    public @Optional String get(@Optional Object object){
        if(object != null) {
            if (object.getClass().isAnnotationPresent(Entity.class)) {
                EntityClass entityClass = entityClassProvider.get(object.getClass());
                EntityField entityField = entityClassNameFieldProvider.get(entityClass);
                if (entityField != null) {
                    return (String) entityField.get(object);
                }
            }
        }

        return null;
    }
}
