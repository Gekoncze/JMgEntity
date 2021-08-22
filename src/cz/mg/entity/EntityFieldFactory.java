package cz.mg.entity;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public @Service class EntityFieldFactory {
    public @Mandatory EntityField create(@Mandatory EntityClass entityClass, @Mandatory Field field){
        if(isEntityField(field)){
            if(Modifier.isPublic(field.getModifiers())){
                return new EntityField(entityClass, field);
            } else {
                throw new IllegalArgumentException("Entity field '" + entityClass.getName() + "." + field.getName() + "' must be public.");
            }
        } else {
            throw new IllegalArgumentException("Missing entity field annotation for field '" + entityClass.getName() + "." + field.getName() + "'.");
        }
    }

    private @Mandatory boolean isEntityField(@Mandatory Field field){
        return field.isAnnotationPresent(Value.class)
            || field.isAnnotationPresent(Part.class)
            || field.isAnnotationPresent(Link.class)
            || field.isAnnotationPresent(Shared.class)
            || field.isAnnotationPresent(Cache.class);
    }
}
