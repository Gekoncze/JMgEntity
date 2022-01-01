package cz.mg.entity.explorer;

import cz.mg.collections.list.List;
import cz.mg.entity.explorer.services.ReadService;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestEnum;
import utilities.TestInitializer;
import utilities.TestSubclass;


public class ReadServiceTest implements Test {
    public static void main(String[] args) {
        new TestInitializer().initialize();
        new SingleTestClassRunner().run(ReadServiceTest.class);
    }

    @TestCase(order = 1)
    public void testReadFromEntity(){
        ReadService readService = new ReadService();
        TestSubclass entity = new TestSubclass();

        entity.booleanValue = true;
        entity.byteValue = 7;
        entity.characterValue = 'x';
        entity.doubleValue = 3.14;
        entity.enumValue = TestEnum.SATURDAY;
        entity.floatValue = 9.81f;
        entity.integerValue = 69;
        entity.longValue = 88L;
        entity.next = null;
        entity.stringValue = "test";

        // field order is alphabetical
        assertExceptionThrown(
            ArrayIndexOutOfBoundsException.class,
            () -> readService.read(entity, -1)
        );
        assertEquals(entity.booleanValue, readService.read(entity, 0));
        assertEquals(entity.byteValue, readService.read(entity, 1));
        assertEquals(entity.characterValue, readService.read(entity, 2));
        assertEquals(entity.doubleValue, readService.read(entity, 3));
        assertEquals(entity.enumValue, readService.read(entity, 4));
        assertEquals(entity.floatValue, readService.read(entity, 5));
        assertEquals(entity.integerValue, readService.read(entity, 6));
        assertEquals(entity.longValue, readService.read(entity, 7));
        assertEquals(entity.next, readService.read(entity, 8));
        assertEquals(entity.stringValue, readService.read(entity, 9));
        assertExceptionThrown(
            ArrayIndexOutOfBoundsException.class,
            () -> readService.read(entity, 10)
        );
    }

    @TestCase(order = 2)
    public void testReadFromList(){
        ReadService readService = new ReadService();
        List<TestEnum> list = new List<>();

        TestEnum first = TestEnum.SATURDAY;
        TestEnum second = TestEnum.SUNDAY;
        list.addLast(first);
        list.addLast(second);

        assertExceptionThrown(
            ArrayIndexOutOfBoundsException.class,
            () -> readService.read(list, -1)
        );
        assertSame(first, readService.read(list, 0));
        assertSame(second, readService.read(list, 1));
        assertExceptionThrown(
            ArrayIndexOutOfBoundsException.class,
            () -> readService.read(list, 2)
        );
    }
}
