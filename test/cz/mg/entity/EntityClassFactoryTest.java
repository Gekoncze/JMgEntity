package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityClassFactoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassFactoryTest.class);
    }

    @TestCase(order = 1)
    public void testCannotCreateNonEntityClass(){
        EntityClassFactory entityClassFactory = new EntityClassFactory();

        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityClassFactory.create(TestEnum.class, new List<>())
        );
    }

    @TestCase(order = 2)
    public void testMustHaveParameterlessConstructor(){
        EntityClassFactory entityClassFactory = new EntityClassFactory();

        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> entityClassFactory.create(TestImmutableEntity.class, new List<>())
        );
    }

    @TestCase(order = 3)
    public void testCreate() throws Exception {
        EntityClassFactory entityClassFactory = new EntityClassFactory();
        EntityClass entityClass = entityClassFactory.create(TestEntity.class, new List<>());

        assertNotNull(entityClass);
        assertEquals(3, entityClass.getFields().count());
        assertEquals(TestEntity.class.getField("enumeration"), entityClass.getFields().get(0).getField());
        assertEquals(TestEntity.class.getField("next"), entityClass.getFields().get(1).getField());
        assertEquals(TestEntity.class.getField("number"), entityClass.getFields().get(2).getField());
    }
}
