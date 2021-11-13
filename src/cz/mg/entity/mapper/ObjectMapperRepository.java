package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;


public @Utility class ObjectMapperRepository {
    private final @Mandatory @Part Map<@Link Class, @Part ObjectMapper> classMap = new Map<>();
    private final @Mandatory @Part Map<@Link String, @Part ObjectMapper> nameMap = new Map<>();

    public ObjectMapperRepository() {
    }

    public @Mandatory ObjectMapper get(@Mandatory Class clazz){
        ObjectMapper objectMapper = classMap.get(clazz);
        if(objectMapper != null){
            return objectMapper;
        } else {
            throw new IllegalArgumentException("No object mapper found for class '" + clazz.getSimpleName() + "'.");
        }
    }

    public @Mandatory ObjectMapper get(@Mandatory String name){
        ObjectMapper objectMapper = nameMap.get(name);
        if(objectMapper != null){
            return objectMapper;
        } else {
            throw new IllegalArgumentException("No object mapper found for name '" + name + "'.");
        }
    }

    public <T> void set(@Mandatory Class<T> clazz, @Optional ObjectMapper<T> objectMapper){
        if(objectMapper != null){
            classMap.set(clazz, objectMapper);
            nameMap.set(objectMapper.getName(), objectMapper);
        } else {
            classMap.remove(clazz);
        }
    }

    public boolean contains(@Mandatory Class clazz){
        return classMap.get(clazz) != null;
    }

    public boolean contains(@Mandatory String name){
        return nameMap.get(name) != null;
    }

    public @Mandatory ReadableList<Class> getAvailableClasses(){
        return classMap.keys();
    }

    public @Mandatory ReadableList<String> getAvailableNames(){
        return nameMap.keys();
    }
}
