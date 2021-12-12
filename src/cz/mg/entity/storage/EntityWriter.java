package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.mapper.Mapper;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.connections.SqliteConnection;


public @Service class EntityWriter {
    private final @Mandatory @Shared ElementTableWriter elementTableWriter = new ElementTableWriter();

    public void write(@Mandatory String path, @Optional Object entity, @Mandatory Mapper mapper){
        try(SqlConnection connection = new SqliteConnection(path)){
            connection.begin();
            elementTableWriter.write(connection, mapper.map(entity));
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
