package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestLeaf;
import utilities.illegal.TestPrivateFieldEntity;
import utilities.illegal.TestUnannotatedFieldEntity;

import java.lang.reflect.Field;


public class EntityFieldFactoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityFieldFactoryTest.class);
    }

    @TestCase(order = 1)
    public void testCreate() throws Exception {
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        Field field = TestLeaf.class.getField("integerValue");
        EntityField entityField = entityFieldFactory.create(entityClass, field);
        assertNotNull(entityField);
        assertEquals(field, entityField.getField());
    }

    @TestCase(order = 2)
    public void testPrivateField() {
        EntityClass entityClass = new EntityClass(TestPrivateFieldEntity.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityFieldFactory.create(
                entityClass,
                TestPrivateFieldEntity.class.getDeclaredField("integerValue")
            )
        );
    }

    @TestCase(order = 3)
    public void testUnannotatedField(){
        EntityClass entityClass = new EntityClass(TestUnannotatedFieldEntity.class, new List<>(), new List<>());
        EntityFieldFactory entityFieldFactory = new EntityFieldFactory();
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityFieldFactory.create(
                entityClass,
                TestUnannotatedFieldEntity.class.getField("integerValue")
            )
        );
    }
}
