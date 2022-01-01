package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestLeaf;


public class EntityFieldTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityFieldTest.class);
    }

    @TestCase(order = 1)
    public void testGet() throws Exception {
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        EntityField entityField = new EntityField(entityClass, TestLeaf.class.getField("integerValue"));
        TestLeaf entity = new TestLeaf();
        entity.integerValue = 7;
        assertEquals(entity.integerValue, entityField.get(entity));
    }

    @TestCase(order = 2)
    public void testSet() throws Exception {
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        EntityField entityField = new EntityField(entityClass, TestLeaf.class.getField("integerValue"));
        TestLeaf entity = new TestLeaf();
        entityField.set(entity, 7);
        assertEquals(7, entity.integerValue);
    }

    @TestCase(order = 3)
    public void testIllegalSet() throws Exception {
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        EntityField entityField = new EntityField(entityClass, TestLeaf.class.getField("integerValue"));
        TestLeaf entity = new TestLeaf();
        assertExceptionThrown(IllegalArgumentException.class, () -> {
            entityField.set(entity, 'x');
        });
    }
}
