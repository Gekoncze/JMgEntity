package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestBranch;
import utilities.TestLeaf;
import utilities.TestRoot;
import utilities.TestSubclass;


public class EntityClassInitializerTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassInitializerTest.class);
    }

    @TestCase(order = 1)
    public void testInitialize(){
        TestRoot.entity = null;
        TestBranch.entity = null;
        TestLeaf.entity = null;
        TestSubclass.entity = null;

        new EntityClassInitializer().initialize(new List<>(
            TestRoot.class,
            TestBranch.class,
            TestLeaf.class,
            TestSubclass.class
        ));

        assertNotNull(TestRoot.entity);
        assertNotNull(TestBranch.entity);
        assertNotNull(TestLeaf.entity);
        assertNotNull(TestSubclass.entity);

        assertEquals(0, TestRoot.entity.getSubclasses().count());
        assertEquals(0, TestBranch.entity.getSubclasses().count());
        assertEquals(1, TestLeaf.entity.getSubclasses().count());
        assertEquals(0, TestSubclass.entity.getSubclasses().count());

        assertSame(TestSubclass.entity, TestLeaf.entity.getSubclasses().getFirst());
    }

    @TestCase(order = 2)
    public void testMissingStaticField(){
        assertExceptionThrown(
            IllegalArgumentException.class,
            () -> {
                new EntityClassInitializer().initialize(new List<>(
                    TestMissingStaticFieldEntity.class
                ));
            }
        );
    }

    public static @Entity class TestMissingStaticFieldEntity {
        public @Value Integer integerValue;
    }
}
