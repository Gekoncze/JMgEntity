package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestLeaf;

import java.lang.reflect.Field;


public class EntityFieldFactoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityFieldFactoryTest.class);
    }

    @TestCase(order = 1)
    public void testCreate() throws Exception {
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        Field field = TestLeaf.class.getField("integerValue");
        EntityField entityField = new EntityFieldFactory().create(entityClass, field);
        assertNotNull(entityField);
        assertEquals(field, entityField.getField());
    }

    @TestCase(order = 2)
    public void testPrivateField() {
        EntityClass entityClass = new EntityClass(TestPrivateFieldEntity.class, new List<>(), new List<>());
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityFieldFactory().create(
                entityClass,
                TestPrivateFieldEntity.class.getDeclaredField("integerValue")
            )
        );
    }

    @TestCase(order = 3)
    public void testUnannotatedField(){
        EntityClass entityClass = new EntityClass(TestUnannotatedFieldEntity.class, new List<>(), new List<>());
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityFieldFactory().create(
                entityClass,
                TestUnannotatedFieldEntity.class.getField("integerValue")
            )
        );
    }

    @TestCase(order = 4)
    public void testFinalField(){
        EntityClass entityClass = new EntityClass(TestFinalFieldEntity.class, new List<>(), new List<>());
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityFieldFactory().create(
                entityClass,
                TestFinalFieldEntity.class.getField("integerValue")
            )
        );
    }

    public static @Entity class TestPrivateFieldEntity {
        public static EntityClass entity;

        private @Value Integer integerValue;
    }

    public static @Entity class TestUnannotatedFieldEntity {
        public static EntityClass entity;

        public Integer integerValue;
    }

    public static @Entity class TestFinalFieldEntity {
        public static EntityClass entity;

        public final Integer integerValue = 0;
    }
}
