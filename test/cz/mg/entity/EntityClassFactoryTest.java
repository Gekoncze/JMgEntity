package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestEnum;
import utilities.TestLeaf;


public class EntityClassFactoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassFactoryTest.class);
    }

    @TestCase(order = 1)
    public void testCreate() throws Exception {
        EntityClass entityClass = new EntityClassFactory().create(TestLeaf.class);

        assertNotNull(entityClass);
        assertEquals(9, entityClass.getFields().count());
        assertEquals(TestLeaf.class.getField("booleanValue"), entityClass.getFields().get(0).getField());
        assertEquals(TestLeaf.class.getField("byteValue"), entityClass.getFields().get(1).getField());
        assertEquals(TestLeaf.class.getField("characterValue"), entityClass.getFields().get(2).getField());
        assertEquals(TestLeaf.class.getField("doubleValue"), entityClass.getFields().get(3).getField());
        assertEquals(TestLeaf.class.getField("enumValue"), entityClass.getFields().get(4).getField());
        assertEquals(TestLeaf.class.getField("floatValue"), entityClass.getFields().get(5).getField());
        assertEquals(TestLeaf.class.getField("integerValue"), entityClass.getFields().get(6).getField());
        assertEquals(TestLeaf.class.getField("longValue"), entityClass.getFields().get(7).getField());
        assertEquals(TestLeaf.class.getField("stringValue"), entityClass.getFields().get(8).getField());
    }

    @TestCase(order = 2)
    public void testPrivateClass(){
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(TestPrivateClassEntity.class)
        );
    }

    @TestCase(order = 3)
    public void testUnannotatedClass(){
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(int.class)
        );

        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(String.class)
        );

        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(TestEnum.class)
        );

        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(TestUnannotatedClassEntity.class)
        );
    }

    @TestCase(order = 4)
    public void testParametricConstructor(){
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> new EntityClassFactory().create(TestParametricConstructorEntity.class)
        );
    }

    @TestCase(order = 5)
    public void testAllowedParametricConstructor(){
        assertExceptionNotThrown(
            () -> new EntityClassFactory().create(TestAllowedParametricConstructorEntity.class)
        );
    }

    private static @Entity class TestPrivateClassEntity {
        public static EntityClass entity;

        public @Value Integer integerValue;
    }

    public static class TestUnannotatedClassEntity {
        public static EntityClass entity;

        public @Value Integer integerValue;
    }

    public static @Entity class TestParametricConstructorEntity {
        public static EntityClass entity;

        public @Value Integer integerValue;

        public TestParametricConstructorEntity(@Value Integer integerValue) {
            this.integerValue = integerValue;
        }
    }

    public static @Entity class TestAllowedParametricConstructorEntity {
        public static EntityClass entity;

        public @Value Integer integerValue;

        public TestAllowedParametricConstructorEntity() {
        }

        public TestAllowedParametricConstructorEntity(@Value Integer integerValue) {
            this.integerValue = integerValue;
        }
    }
}
