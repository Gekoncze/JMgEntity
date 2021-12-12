package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.entity.mapper.Mapper;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;


public @Service class EntityReader {
    private final @Mandatory @Shared ElementTableReader elementTableReader = new ElementTableReader();

    public @Mandatory Object readMandatory(@Mandatory String path, @Mandatory Mapper mapper){
        Object entity = read(path, mapper);
        if(entity != null){
            return entity;
        } else {
            throw new NullPointerException();
        }
    }

    public @Optional Object read(@Mandatory String path, @Mandatory Mapper mapper){
        return mapper.unmap(loadElements(path));
    }

    private @Mandatory List<Element> loadElements(@Mandatory String path){
        try(SqlConnection connection = new SqliteConnection(path)){
            connection.begin();
            return elementTableReader.read(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
