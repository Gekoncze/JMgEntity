package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public @Service class EntityClassProvider {
    public @Mandatory EntityClass get(@Mandatory Class clazz){
        if(clazz.isAnnotationPresent(Entity.class)){
            try {
                Field entityField = clazz.getDeclaredField("entity");
                if(EntityClass.class.equals(entityField.getType())){
                    if(Modifier.isStatic(entityField.getModifiers())){
                        EntityClass entityClass = (EntityClass) entityField.get(null);
                        if(entityClass != null){
                            return entityClass;
                        } else {
                            throw new IllegalStateException("Missing entity class for class '" + clazz.getSimpleName() + "'.");
                        }
                    } else {
                        throw new IllegalArgumentException("Entity class field must be static for class '" + clazz.getSimpleName() + "'.");
                    }
                } else {
                    throw new IllegalArgumentException("Expected entity class type on entity field for class '" +  clazz.getSimpleName()+ "'.");
                }
            } catch (ReflectiveOperationException e){
                throw new IllegalArgumentException("Could not get entity class of '" + clazz.getSimpleName() + "'.", e);
            }
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }
}
