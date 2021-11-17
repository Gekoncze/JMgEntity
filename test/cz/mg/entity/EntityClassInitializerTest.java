package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityClassInitializerTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassInitializerTest.class);
    }

    @TestCase
    public void test(){
        new EntityClassInitializer().init(new List<>(
            TestEntity.class,
            TestEntityChild.class,
            TestEntityThird.class
        ));

        assertNotNull(TestEntity.entity);
        assertNotNull(TestEntityChild.entity);
        assertNotNull(TestEntityThird.entity);

        assertEquals(1, TestEntity.entity.getSubclasses().count());
        assertEquals(0, TestEntityChild.entity.getSubclasses().count());
        assertEquals(0, TestEntityThird.entity.getSubclasses().count());

        assertSame(TestEntityChild.entity, TestEntity.entity.getSubclasses().getFirst());
    }
}
