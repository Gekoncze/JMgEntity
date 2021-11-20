package cz.mg.entity.validator;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.entity.TestEntity;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityGraphValidatorTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityGraphValidatorTest.class);
    }

    @TestCase(order = 1)
    public void testValidationPass(){
        new EntityClassInitializer().init(new List<>(TestEntity.class));

        TestEntity entity01 = new TestEntity();
        entity01.number = 1;

        TestEntity entity02 = new TestEntity();
        entity02.number = 2;

        TestEntity entity03 = new TestEntity();
        entity03.number = 3;

        entity01.next = entity02;
        entity02.next = entity03;
        entity03.next = entity01;

        assertExceptionNotThrown(() -> {
            new EntityGraphValidator().validate(entity01);
        });
    }

    @TestCase(order = 2)
    public void testValidationFail(){
        new EntityClassInitializer().init(new List<>(TestEntity.class));

        TestEntity entity01 = new TestEntity();
        entity01.number = null;

        TestEntity entity02 = new TestEntity();
        entity02.number = null;

        TestEntity entity03 = new TestEntity();
        entity03.number = null;

        entity01.next = entity02;
        entity02.next = entity03;
        entity03.next = entity01;

        ValidationsException exception = assertExceptionThrown(ValidationsException.class, () -> {
            new EntityGraphValidator().validate(entity01);
        });

        assertEquals(3, exception.getExceptions().count());
    }
}
