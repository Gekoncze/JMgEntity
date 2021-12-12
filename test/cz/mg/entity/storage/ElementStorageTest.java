package cz.mg.entity.storage;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.sql.light.connection.connections.SqliteConnection;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class ElementStorageTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(ElementStorageTest.class);
    }

    @TestCase
    public void testReadWrite(){
        SqliteConnection connection = new SqliteConnection(":memory:");
        connection.begin();

        List<Element> savedElements = new List<>();
        savedElements.addLast(createElement("first", "abc"));
        savedElements.addLast(createElement("last", null, 7, 1, 4));

        ElementTableWriter writer = new ElementTableWriter();
        writer.write(connection, savedElements);

        ElementTableReader reader = new ElementTableReader();
        List<Element> loadedElements = reader.read(connection);

        assertEquals(2, loadedElements.count());

        assertEquals("first", loadedElements.getFirst().name);
        assertEquals("abc", loadedElements.getFirst().value);
        assertNotNull(loadedElements.getFirst().fields);
        assertEquals(0, loadedElements.getFirst().fields.count());

        assertEquals("last", loadedElements.getLast().name);
        assertNull(loadedElements.getLast().value);
        assertNotNull(loadedElements.getLast().fields);
        assertEquals(3, loadedElements.getLast().fields.count());
        assertEquals(7, loadedElements.getLast().fields.get(0));
        assertEquals(1, loadedElements.getLast().fields.get(1));
        assertEquals(4, loadedElements.getLast().fields.get(2));
    }

    private @Mandatory Element createElement(
        @Mandatory String name,
        @Optional String value,
        @Mandatory Integer... fields
    ){
        Element element = new Element();
        element.name = name;
        element.value = value;
        element.fields = new List<>(fields);
        return element;
    }
}
