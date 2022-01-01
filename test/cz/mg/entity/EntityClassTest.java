package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestLeaf;


public class EntityClassTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassTest.class);
    }

    @TestCase(order = 1)
    public void testGetField() throws Exception {
        List<EntityField> fields = new List<>();
        EntityClass entityClass = new EntityClass(TestLeaf.class, fields, new List<>());
        EntityField integerValueField = new EntityField(entityClass, TestLeaf.class.getField("integerValue"));
        fields.addLast(integerValueField);
        assertEquals(integerValueField, entityClass.getField("integerValue"));
        assertNull(entityClass.getField("xyz"));
    }

    @TestCase(order = 2)
    public void testGetName(){
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        assertEquals(TestLeaf.class.getSimpleName(), entityClass.getName());
    }

    @TestCase(order = 3)
    public void testNewInstance(){
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        TestLeaf testLeaf = (TestLeaf) entityClass.newInstance();
        assertNotNull(testLeaf);
    }
}
