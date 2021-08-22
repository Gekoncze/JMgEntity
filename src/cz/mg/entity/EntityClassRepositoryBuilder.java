package cz.mg.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListSorter;
import cz.mg.collections.map.Map;

import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public @Utility class EntityClassRepositoryBuilder {
    private final @Mandatory @Part Set<@Link Class> classes = new HashSet<>();
    private final @Mandatory @Link EntityClassFactory entityClassFactory = new EntityClassFactory();

    public @Mandatory EntityClassRepositoryBuilder addClass(@Mandatory Class clazz){
        classes.add(clazz);
        return this;
    }

    public @Mandatory EntityClassRepositoryBuilder addModule(@Mandatory EntityClassModule module){
        for(Class clazz : module){
            addClass(clazz);
        }
        return this;
    }

    public @Mandatory EntityClassRepository build(){
        EntityClassRepository repository = new EntityClassRepository();

        Map<Class, List<EntityClass>> subclassMap = new Map<>();

        for(Class clazz : classes){
            if(isInstantiable(clazz)){
                resolve(clazz, repository, subclassMap);
            }
        }

        for(Class clazz : repository.getAvailableClasses()){
            EntityClass entityClass = repository.get(clazz);
            ListSorter.sortInPlace(
                subclassMap.get(entityClass.getClazz()),
                Comparator.comparing(EntityClass::getName)
            );
        }

        return repository;
    }

    private boolean isInstantiable(@Mandatory Class clazz){
        if(!Modifier.isAbstract(clazz.getModifiers())){
            try {
                clazz.getConstructor();
                return true;
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

    private void resolve(
        @Mandatory Class clazz,
        @Mandatory EntityClassRepository repository,
        @Mandatory Map<Class, List<EntityClass>> subclassMap
    ){
        EntityClass entityClass = getOrCreate(clazz, repository, subclassMap);
        Class current = clazz;
        while(current != null){
            if(current != Object.class){
                getOrCreate(current, repository, subclassMap);
                subclassMap.get(current).addLast(entityClass);
            }
            current = current.getSuperclass();
        }
    }

    private @Mandatory EntityClass getOrCreate(
        @Mandatory Class clazz,
        @Mandatory EntityClassRepository repository,
        @Mandatory Map<Class, List<EntityClass>> subclassMap
    ){
        if(repository.contains(clazz)){
            return repository.get(clazz);
        } else {
            List<EntityClass> subclasses = new ArrayList<>();
            EntityClass entityClass = entityClassFactory.create(clazz, subclasses);
            repository.set(clazz, entityClass);
            subclassMap.set(clazz, subclasses);
            return entityClass;
        }
    }
}
