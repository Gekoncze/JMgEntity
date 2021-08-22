package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;

import java.lang.reflect.Field;
import java.util.Comparator;


public @Service class EntityClassFactory {
    private final @Mandatory @Link EntityFieldFactory entityFieldFactory = new EntityFieldFactory();

    public @Mandatory EntityClass create(@Mandatory Class clazz, @Mandatory List<EntityClass> subclasses){
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
        }

        try {
            clazz.getConstructor();
        } catch (ReflectiveOperationException e){
            throw new IllegalArgumentException("Missing parameterless constructor for class '" + clazz.getSimpleName() + "'.");
        }

        List<EntityField> fields = new ArrayList<>();
        EntityClass entityClass = new EntityClass(clazz, fields, subclasses);

        Class current = clazz;
        while(current != null){
            for(Field field : current.getDeclaredFields()){
                fields.addLast(entityFieldFactory.create(entityClass, field));
            }
            current = current.getSuperclass();
        }

        ListSorter.sortInPlace(fields, Comparator.comparing(EntityField::getName));
        ListSorter.sortInPlace(subclasses, Comparator.comparing(EntityClass::getName));

        return entityClass;
    }
}
