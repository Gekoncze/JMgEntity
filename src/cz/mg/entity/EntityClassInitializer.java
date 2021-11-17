package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;


public @Service class EntityClassInitializer {
    private final @Mandatory @Shared EntityClassFactory factory = new EntityClassFactory();
    private final @Mandatory @Shared EntityClassProvider provider = new EntityClassProvider();

    public void init(@Mandatory List<Class> classes){
        List<EntityClass> entityClasses = new List<>();

        for(Class clazz : classes){
            if(clazz.isAnnotationPresent(Entity.class)){
                EntityClass entityClass = factory.create(clazz);
                entityClasses.addLast(entityClass);
                set(clazz, entityClass);
            }
        }

        for(EntityClass entityClass : entityClasses){
            if(entityClass.getClazz().getSuperclass().isAnnotationPresent(Entity.class)){
                provider.get(entityClass.getClazz().getSuperclass()).subclasses.addLast(entityClass);
            }
        }

        for(EntityClass entityClass : entityClasses){
            ListSorter.sortInPlace(entityClass.subclasses, Comparator.comparing(EntityClass::getName));
        }
    }

    public void set(@Mandatory Class clazz, @Mandatory EntityClass entityClass){
        if(clazz.isAnnotationPresent(Entity.class)){
            try {
                Field entityField = clazz.getDeclaredField("entity");
                if(EntityClass.class.equals(entityField.getType())){
                    if(Modifier.isStatic(entityField.getModifiers())){
                        entityField.set(null, entityClass);
                    } else {
                        throw new IllegalArgumentException("Entity class field must be static for class '" + clazz.getSimpleName() + "'.");
                    }
                } else {
                    throw new IllegalArgumentException("Expected entity class type on entity field for class '" +  clazz.getSimpleName()+ "'.");
                }
            } catch (ReflectiveOperationException e){
                throw new IllegalArgumentException("Could not set entity class of '" + clazz.getSimpleName() + "'.", e);
            }
        } else {
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }
    }
}
