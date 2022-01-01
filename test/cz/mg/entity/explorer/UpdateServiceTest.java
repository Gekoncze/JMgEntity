package cz.mg.entity.explorer;

import cz.mg.collections.list.List;
import cz.mg.entity.explorer.services.UpdateService;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestEnum;
import utilities.TestInitializer;
import utilities.TestMapper;
import utilities.TestSubclass;


public class UpdateServiceTest implements Test {
    public static void main(String[] args) {
        new TestInitializer().initialize();
        new SingleTestClassRunner().run(UpdateServiceTest.class);
    }

    @TestCase(order = 1)
    public void testUpdateEntity(){
        Explorer explorer = new Explorer(TestSubclass.entity, new TestMapper());
        UpdateService updateService = new UpdateService();
        TestSubclass entity = new TestSubclass();

        Boolean booleanValue = true;
        Byte byteValue = 7;
        Character characterValue = 'x';
        Double doubleValue = 3.14;
        Enum enumValue = TestEnum.SATURDAY;
        Float floatValue = 9.81f;
        Integer integerValue = 69;
        Long longValue = 88L;
        TestSubclass next = null;
        String stringValue = "test";

        // field order is alphabetical
        explorer.getTransactionManager().transaction(() -> {
            assertExceptionThrown(
                ArrayIndexOutOfBoundsException.class,
                () -> updateService.update(explorer, entity, -1, null)
            );
            updateService.update(explorer, entity, 0, booleanValue);
            assertEquals(booleanValue, entity.booleanValue);
            updateService.update(explorer, entity, 1, byteValue);
            assertEquals(byteValue, entity.byteValue);
            updateService.update(explorer, entity, 2, characterValue);
            assertEquals(characterValue, entity.characterValue);
            updateService.update(explorer, entity, 3, doubleValue);
            assertEquals(doubleValue, entity.doubleValue);
            updateService.update(explorer, entity, 4, enumValue);
            assertEquals(enumValue, entity.enumValue);
            updateService.update(explorer, entity, 5, floatValue);
            assertEquals(floatValue, entity.floatValue);
            updateService.update(explorer, entity, 6, integerValue);
            assertEquals(integerValue, entity.integerValue);
            updateService.update(explorer, entity, 7, longValue);
            assertEquals(longValue, entity.longValue);
            updateService.update(explorer, entity, 8, next);
            assertEquals(next, entity.next);
            updateService.update(explorer, entity, 9, stringValue);
            assertEquals(stringValue, entity.stringValue);
            assertExceptionThrown(
                ArrayIndexOutOfBoundsException.class,
                () -> updateService.update(explorer, entity, 10, null)
            );
        });
    }

    @TestCase(order = 2)
    public void testUpdateList(){
        Explorer explorer = new Explorer(TestSubclass.entity, new TestMapper());
        UpdateService updateService = new UpdateService();
        List<TestEnum> list = new List<>();

        list.addLast(null);
        list.addLast(null);
        TestEnum first = TestEnum.SATURDAY;
        TestEnum second = TestEnum.SUNDAY;

        explorer.getTransactionManager().transaction(() -> {
            assertExceptionThrown(
                ArrayIndexOutOfBoundsException.class,
                () -> updateService.update(explorer, list, -1, null)
            );
            updateService.update(explorer, list, 0, first);
            assertSame(first, list.getFirst());
            updateService.update(explorer, list, 1, second);
            assertSame(second, list.getLast());
            assertExceptionThrown(
                ArrayIndexOutOfBoundsException.class,
                () -> updateService.update(explorer, list, 2, null)
            );
        });
    }
}
