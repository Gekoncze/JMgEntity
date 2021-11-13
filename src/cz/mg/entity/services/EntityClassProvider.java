package cz.mg.entity.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.EntityClass;

import java.lang.reflect.Field;


public @Service class EntityClassProvider {
    public @Mandatory EntityClass get(@Mandatory Class clazz){
        if(clazz.isAnnotationPresent(Entity.class)){
            try {
                Field entityField = clazz.getDeclaredField("entity");
                if(EntityClass.class.equals(entityField.getType())){
                    EntityClass entityClass = (EntityClass) entityField.get(null);
                    if(entityClass != null){
                        return entityClass;
                    } else {
                        throw new IllegalStateException("Missing entity class for class '" + clazz.getSimpleName() + "'.");
                    }
                } else {
                    throw new IllegalArgumentException("Expected entity class type on entity field for class '" +  clazz.getSimpleName()+ "'.");
                }
            } catch (ReflectiveOperationException e){
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }
}
