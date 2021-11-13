package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class EntityFieldTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new EntityFieldTest());
    }

    @TestCase(order = 0)
    public void testGet() throws Exception {
        EntityClass entityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        EntityField entityField = new EntityField(entityClass, TestEntity.class.getField("number"));
        TestEntity entity = new TestEntity();
        entity.number = 7;
        assertEquals(entity.number, entityField.get(entity));
    }

    @TestCase(order = 1)
    public void testSet() throws Exception {
        EntityClass entityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        EntityField entityField = new EntityField(entityClass, TestEntity.class.getField("number"));
        TestEntity entity = new TestEntity();
        entityField.set(entity, 7);
        assertEquals(7, entity.number);
    }
}