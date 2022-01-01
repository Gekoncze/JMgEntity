package cz.mg.entity.validator;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;
import cz.mg.entity.TestEntity;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityValidatorTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityValidatorTest.class);
    }

    @TestCase(order = 1)
    public void testValidationPass(){
        new EntityClassInitializer().initialize(new List<>(TestEntity.class));

        TestEntity entity = new TestEntity();
        entity.number = 1;

        assertExceptionNotThrown(() -> {
            new EntityValidator().validate(entity);
        });
    }

    @TestCase(order = 2)
    public void testValidationFail(){
        new EntityClassInitializer().initialize(new List<>(TestEntity.class));

        TestEntity entity = new TestEntity();
        entity.number = null;

        assertExceptionThrown(ValidationException.class, () -> {
            new EntityValidator().validate(entity);
        });
    }
}
