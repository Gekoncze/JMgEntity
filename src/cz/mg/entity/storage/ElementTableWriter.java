package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.sql.light.connection.SqlConnection;


public @Service class ElementTableWriter {
    public void write(@Mandatory SqlConnection connection, @Mandatory List<Element> elements){
        ElementTable entityTable = ElementTable.getInstance();
        ElementFieldTable fieldTable = ElementFieldTable.getInstance();

        entityTable.createOrReplace(connection);
        fieldTable.createOrReplace(connection);

        int entityId = 0;
        for(Element element : elements){
            entityTable.createRow(connection, entityId, element);

            int fieldId = 0;
            for(Integer field : element.fields){
                fieldTable.createRow(connection, entityId, fieldId, field);
                fieldId++;
            }

            entityId++;
        }
    }
}
