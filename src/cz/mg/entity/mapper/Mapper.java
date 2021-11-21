package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;


public @Utility class Mapper<T> {
    private final @Mandatory @Part ObjectMapperRepository objectMappers;

    public Mapper(@Mandatory ObjectMapperRepository objectMappers) {
        this.objectMappers = objectMappers;
    }

    public @Mandatory List<Element> map(@Optional T object){
        List<Element> elements = new List<>();
        Map<Object, Integer> cache = new Map<>();
        map(elements, cache, object);
        return elements;
    }

    private @Optional Integer map(@Mandatory List<Element> elements, @Mandatory Map<Object, Integer> cache, @Optional Object object){
        if(object == null){
            return null;
        }

        if(cache.get(object) != null){
            return cache.get(object);
        }

        int id = elements.count();

        ObjectMapper objectMapper = objectMappers.get(object.getClass());
        Element element = new Element();
        element.name = objectMapper.getName();
        element.value = objectMapper.getValue(object);
        element.fields = new List<>();

        elements.addLast(element);
        cache.set(object, id);

        for(Object child : objectMapper.getFields(object)){
            element.fields.addLast(map(elements, cache, child));
        }

        return id;
    }

    public @Optional T unmap(@Mandatory List<Element> elements){
        Map<Integer, Object> cache = new Map<>();
        return (T) unmap(elements, cache, 0);
    }

    private @Optional Object unmap(@Mandatory List<Element> elements, @Mandatory Map<Integer, Object> cache, @Optional Integer id){
        if(id == null){
            return null;
        }

        if(cache.get(id) != null){
            return cache.get(id);
        }

        Element element = elements.get(id);
        if(element == null){
            throw new NullPointerException("Could not find entity with id " + id + ".");
        }

        ObjectMapper objectMapper = objectMappers.get(element.name);
        Object object = objectMapper.create(element.value);

        cache.set(id, object);

        List<Object> fields = new List<>();
        for(Integer field : element.fields){
            fields.addLast(unmap(elements, cache, field));
        }
        objectMapper.setFields(object, fields);

        return object;
    }
}
