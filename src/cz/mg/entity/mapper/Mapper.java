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

    public @Mandatory List<Node> map(@Optional T object){
        List<Node> nodes = new List<>();
        Map<Object, Integer> cache = new Map<>();
        map(nodes, cache, object);
        return nodes;
    }

    private @Optional Integer map(@Mandatory List<Node> nodes, @Mandatory Map<Object, Integer> cache, @Optional Object object){
        if(object == null){
            return null;
        }

        if(cache.get(object) != null){
            return cache.get(object);
        }

        int id = nodes.count();

        ObjectMapper objectMapper = objectMappers.get(object.getClass());
        Node node = new Node();
        node.name = objectMapper.getName();
        node.value = objectMapper.getValue(object);
        node.fields = new List<>();

        nodes.addLast(node);
        cache.set(object, id);

        for(Object child : objectMapper.getFields(object)){
            node.fields.addLast(map(nodes, cache, child));
        }

        return id;
    }

    public @Optional T unmap(@Mandatory List<Node> nodes){
        Map<Integer, Object> cache = new Map<>();
        return (T) unmap(nodes, cache, 0);
    }

    private @Optional Object unmap(@Mandatory List<Node> nodes, @Mandatory Map<Integer, Object> cache, @Optional Integer id){
        if(id == null){
            return null;
        }

        if(cache.get(id) != null){
            return cache.get(id);
        }

        Node node = nodes.get(id);
        if(node == null){
            throw new NullPointerException("Could not find entity with id " + id + ".");
        }

        ObjectMapper objectMapper = objectMappers.get(node.name);
        Object object = objectMapper.create(node.value);

        cache.set(id, object);

        List<Object> fields = new List<>();
        for(Integer field : node.fields){
            fields.addLast(unmap(nodes, cache, field));
        }
        objectMapper.setFields(object, fields);

        return object;
    }
}
