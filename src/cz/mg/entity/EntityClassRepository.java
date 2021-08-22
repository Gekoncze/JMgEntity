package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;


public @Utility class EntityClassRepository {
    private final @Mandatory @Part Map<@Link Class, @Part EntityClass> map = new Map<>();

    public EntityClassRepository() {
    }

    public @Mandatory EntityClass get(@Mandatory Class clazz){
        EntityClass entityClass = map.get(clazz);
        if(entityClass != null){
            return entityClass;
        } else {
            throw new IllegalArgumentException("No entity class found for class '" + clazz.getSimpleName() + "'.");
        }
    }

    public void set(@Mandatory Class clazz, @Optional EntityClass entityClass){
        if(entityClass != null){
            if(clazz.isAnnotationPresent(Entity.class)){
                if(entityClass.getClazz() == clazz){
                    map.set(clazz, entityClass);
                } else {
                    throw new IllegalArgumentException("Entity class mismatch: '" + clazz.getSimpleName() + "' vs '" + entityClass.getClazz().getSimpleName() + "'.");
                }
            } else {
                throw new IllegalArgumentException("Missing entity annotation for class '" + clazz.getSimpleName() + "'.");
            }
        } else {
            map.remove(clazz);
        }
    }

    public boolean contains(@Mandatory Class clazz){
        return map.get(clazz) != null;
    }

    public @Mandatory ReadableList<Class> getAvailableClasses(){
        return map.keys();
    }
}
