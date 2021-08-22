package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class EntityClassTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new EntityClassTest());
    }

    @TestCase(order = 0)
    public void testGetField() throws Exception {
        List<EntityField> fields = new List<>();
        EntityClass entityClass = new EntityClass(TestEntity.class, fields, new List<>());
        EntityField numberField = new EntityField(entityClass, TestEntity.class.getField("number"));
        EntityField enumerationField = new EntityField(entityClass, TestEntity.class.getField("enumeration"));
        EntityField nextField = new EntityField(entityClass, TestEntity.class.getField("next"));
        fields.addLast(numberField);
        fields.addLast(enumerationField);
        fields.addLast(nextField);
        assertEquals(numberField, entityClass.getField("number"));
        assertEquals(enumerationField, entityClass.getField("enumeration"));
        assertEquals(nextField, entityClass.getField("next"));
        assertExceptionThrown(() -> entityClass.getField("xyz"));
    }

    @TestCase(order = 1)
    public void testGetName(){
        EntityClass entityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        assertEquals(TestEntity.class.getSimpleName(), entityClass.getName());
    }

    @TestCase(order = 2)
    public void testNewInstance(){
        EntityClass entityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        TestEntity testEntity = (TestEntity) entityClass.newInstance();
        assertNotNull(testEntity);
    }
}
