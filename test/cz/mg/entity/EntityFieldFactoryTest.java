package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import java.lang.reflect.Field;


public class EntityFieldFactoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new EntityFieldFactoryTest());
    }

    @TestCase(order = 0)
    public void testPrivateField() {
        EntityClass entityClass = new EntityClass(TestPrivateEntity.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityFieldFactory.create(entityClass, TestPrivateEntity.class.getDeclaredField("number"))
        );
    }

    @TestCase(order = 1)
    public void testMissingAnnotation(){
        EntityClass entityClass = new EntityClass(TestIllegalEntity.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityFieldFactory.create(entityClass, TestIllegalEntity.class.getField("number"))
        );
    }

    @TestCase(order = 2)
    public void testCreate() throws Exception {
        EntityClass entityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        Field field = TestEntity.class.getField("number");
        EntityField entityField = entityFieldFactory.create(entityClass, field);
        assertNotNull(entityField);
        assertEquals(field, entityField.getField());
    }
}
