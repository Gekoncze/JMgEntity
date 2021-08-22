package cz.mg.entity;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class EntityClassRepositoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new EntityClassRepositoryTest());
    }

    @TestCase
    public void test(){
        EntityClassRepository repository = new EntityClassRepository();

        EntityClass testEntityClass = new EntityClass(TestEntity.class, new List<>(), new List<>());
        EntityClass testEntitySecondClass = new EntityClass(TestEntitySecond.class, new List<>(), new List<>());
        EntityClass testEntityThirdClass = new EntityClass(TestEntityThird.class, new List<>(), new List<>());

        assertEquals(false, repository.contains(TestEntity.class));
        assertEquals(false, repository.contains(TestEntitySecond.class));
        assertEquals(false, repository.contains(TestEntityThird.class));

        assertExceptionThrown(() -> repository.get(TestEntity.class));
        assertExceptionThrown(() -> repository.get(TestEntitySecond.class));
        assertExceptionThrown(() -> repository.get(TestEntityThird.class));

        repository.set(TestEntity.class, testEntityClass);
        repository.set(TestEntitySecond.class, testEntitySecondClass);
        repository.set(TestEntityThird.class, testEntityThirdClass);

        assertEquals(true, repository.contains(TestEntity.class));
        assertEquals(true, repository.contains(TestEntitySecond.class));
        assertEquals(true, repository.contains(TestEntityThird.class));

        assertSame(testEntityClass, repository.get(TestEntity.class));
        assertSame(testEntitySecondClass, repository.get(TestEntitySecond.class));
        assertSame(testEntityThirdClass, repository.get(TestEntityThird.class));

        assertContains(repository.getAvailableClasses(), TestEntity.class);
        assertContains(repository.getAvailableClasses(), TestEntitySecond.class);
        assertContains(repository.getAvailableClasses(), TestEntityThird.class);

        assertExceptionNotThrown(() -> repository.set(TestEntity.class, testEntityClass));
        assertExceptionNotThrown(() -> repository.set(TestEntitySecond.class, testEntitySecondClass));
        assertExceptionNotThrown(() -> repository.set(TestEntityThird.class, testEntityThirdClass));

        repository.set(TestEntity.class, null);
        repository.set(TestEntitySecond.class, null);
        repository.set(TestEntityThird.class, null);

        assertEquals(false, repository.contains(TestEntity.class));
        assertEquals(false, repository.contains(TestEntitySecond.class));
        assertEquals(false, repository.contains(TestEntityThird.class));

        assertExceptionThrown(() -> repository.get(TestEntity.class));
        assertExceptionThrown(() -> repository.get(TestEntitySecond.class));
        assertExceptionThrown(() -> repository.get(TestEntityThird.class));
    }
}
