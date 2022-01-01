package cz.mg.entity.validator;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestChain;


public class EntityGraphValidatorTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityGraphValidatorTest.class);
    }

    @TestCase(order = 1)
    public void testValidationPass(){
        new EntityClassInitializer().initialize(new List<>(TestChain.class));

        TestChain entity01 = new TestChain();
        entity01.name = "first";

        TestChain entity02 = new TestChain();
        entity02.name = "second";

        TestChain entity03 = new TestChain();
        entity03.name = "third";

        entity01.next = entity02;
        entity02.next = entity03;
        entity03.next = entity01;

        assertExceptionNotThrown(() -> {
            new EntityGraphValidator().validate(entity01);
        });
    }

    @TestCase(order = 2)
    public void testValidationFail(){
        new EntityClassInitializer().initialize(new List<>(TestChain.class));

        TestChain entity01 = new TestChain();
        entity01.name = null;

        TestChain entity02 = new TestChain();
        entity02.name = null;

        TestChain entity03 = new TestChain();
        entity03.name = null;

        entity01.next = entity02;
        entity02.next = entity03;
        entity03.next = entity01;

        ValidationsException exception = assertExceptionThrown(
            ValidationsException.class,
            () -> new EntityGraphValidator().validate(entity01)
        );

        assertEquals(3, exception.getExceptions().count());
    }
}
