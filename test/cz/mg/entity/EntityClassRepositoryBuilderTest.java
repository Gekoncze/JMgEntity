package cz.mg.entity;

import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class EntityClassRepositoryBuilderTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new EntityClassRepositoryBuilderTest());
    }

    @TestCase(order = 0)
    public void testAddClass(){
        EntityClassRepository repository = new EntityClassRepositoryBuilder()
            .addClass(TestEntity.class)
            .addClass(TestEntitySecond.class)
            .addClass(TestEntityThird.class)
            .build();

        assertNotNull(repository);

        assertContains(repository.getAvailableClasses(), TestEntity.class);
        assertContains(repository.getAvailableClasses(), TestEntitySecond.class);
        assertContains(repository.getAvailableClasses(), TestEntityThird.class);

        assertContains(
            repository.get(TestEntity.class).getSubclasses(),
            repository.get(TestEntitySecond.class)
        );
    }

    @TestCase(order = 1)
    public void testAddModule(){
        EntityClassRepository repository = new EntityClassRepositoryBuilder()
            .addModule(new TestEntityClassModule())
            .build();

        assertNotNull(repository);

        assertContains(repository.getAvailableClasses(), TestEntity.class);
        assertContains(repository.getAvailableClasses(), TestEntitySecond.class);
        assertContains(repository.getAvailableClasses(), TestEntityThird.class);

        assertContains(
            repository.get(TestEntity.class).getSubclasses(),
            repository.get(TestEntitySecond.class)
        );
    }
}
