package cz.mg.entity.storage;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.sql.light.builder.SqlBuilder;
import cz.mg.sql.light.connection.SqlConnection;
import cz.mg.sql.light.connection.SqlResult;
import cz.mg.sql.light.types.SqliteTypes;


public @Utility class ElementTable {
    private static final String TABLE_NAME = "Element";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String VALUE = "value";

    private static @Optional @Part ElementTable instance = null;

    public static @Mandatory ElementTable getInstance(){
        if(instance == null) instance = new ElementTable();
        return instance;
    }

    private ElementTable() {
    }

    public void createOrReplace(@Mandatory SqlConnection connection){
        if(exists(connection)){
            delete(connection);
        }

        create(connection);
    }

    private boolean exists(@Mandatory SqlConnection connection){
        return connection.executeQuery(
            new SqlBuilder()
                .readTable(TABLE_NAME)
                .build()
        ).getSingleResult().getIntegerMandatory(0) > 0;
    }

    private void delete(@Mandatory SqlConnection connection){
        connection.executeDdl(
            new SqlBuilder()
                .deleteTable(TABLE_NAME)
                .build()
        );
    }

    private void create(@Mandatory SqlConnection connection){
        connection.executeDdl(
            new SqlBuilder()
                .createTable(TABLE_NAME)
                .column(ID, SqliteTypes.INTEGER)
                .column(NAME, SqliteTypes.TEXT)
                .column(VALUE, SqliteTypes.TEXT)
                .build()
        );
    }

    public int rowCount(@Mandatory SqlConnection connection){
        return connection.executeQuery(
            new SqlBuilder()
                .readRow(TABLE_NAME)
                .column("count(*)")
                .build()
        ).getSingleResult().getIntegerMandatory(0);
    }

    public void createRow(@Mandatory SqlConnection connection, int elementId, @Mandatory Element element){
        connection.executeDml(
            new SqlBuilder()
                .createRow(TABLE_NAME)
                .column(ID, elementId)
                .column(NAME, element.name)
                .column(VALUE, element.value)
                .build()
        );
    }

    public @Mandatory Element readRow(@Mandatory SqlConnection connection, int elementId){
        return createElement(
            connection.executeQuery(
                new SqlBuilder()
                    .readRow(TABLE_NAME)
                    .column(NAME)
                    .column(VALUE)
                    .condition(ID, elementId)
                    .build()
            ).getSingleResult()
        );
    }

    private @Mandatory Element createElement(@Mandatory SqlResult result){
        Element element = new Element();
        element.name = (String) result.get(NAME);
        element.value = (String) result.get(VALUE);
        element.fields = new List<>();
        return element;
    }
}
