package cz.mg.entity.storage;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.sql.light.connection.SqlConnection;


public @Service class ElementTableReader {
    public @Mandatory List<Element> read(@Mandatory SqlConnection connection){
        ElementTable elementTable = ElementTable.getInstance();
        ElementFieldTable fieldTable = ElementFieldTable.getInstance();

        List<Element> elements = new List<>();

        int elementCount = elementTable.rowCount(connection);
        for(int elementId = 0; elementId < elementCount; elementId++){
            Element element = elementTable.readRow(connection, elementId);
            int fieldCount = fieldTable.rowCount(connection, elementId);
            for(int fieldId = 0; fieldId < fieldCount; fieldId++){
                element.fields.addLast(fieldTable.readRow(connection, elementId, fieldId));
            }
            elements.addLast(element);
        }

        return elements;
    }
}
