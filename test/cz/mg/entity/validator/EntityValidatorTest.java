package cz.mg.entity.validator;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestRoot;


public class EntityValidatorTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityValidatorTest.class);
    }

    @TestCase(order = 1)
    public void testValidationPass(){
        new EntityClassInitializer().initialize(new List<>(TestRoot.class));

        TestRoot testRoot = new TestRoot();
        testRoot.name = "Test Root";

        assertExceptionNotThrown(() -> {
            new EntityValidator().validate(testRoot);
        });
    }

    @TestCase(order = 2)
    public void testValidationFail(){
        new EntityClassInitializer().initialize(new List<>(TestRoot.class));

        TestRoot testRoot = new TestRoot();
        testRoot.name = null;

        assertExceptionThrown(
            ValidationException.class,
            () -> new EntityValidator().validate(testRoot)
        );
    }
}
