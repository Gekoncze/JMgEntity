package cz.mg.entity;

import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityClassProviderTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityClassProviderTest.class);
    }

    @TestCase
    public void test(){
        EntityClass entityClass = new EntityClassFactory().create(TestEntity.class);
        TestEntity.entity = entityClass;
        assertSame(entityClass, new EntityClassProvider().get(TestEntity.class));
    }
}
