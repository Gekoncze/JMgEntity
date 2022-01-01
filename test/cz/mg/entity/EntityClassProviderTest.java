package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestLeaf;


public class EntityClassProviderTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassProviderTest.class);
    }

    @TestCase(order = 1)
    public void testGet(){
        EntityClass entityClass = new EntityClass(TestLeaf.class, new List<>(), new List<>());
        TestLeaf.entity = entityClass;
        assertSame(entityClass, new EntityClassProvider().get(TestLeaf.class));
    }

    @TestCase(order = 2)
    public void testMissing(){
        TestLeaf.entity = null;
        assertExceptionThrown(
            IllegalStateException.class,
            () -> new EntityClassProvider().get(TestLeaf.class)
        );
    }
}
