package cz.mg.entity;

import cz.mg.test.runner.BulkTestRunner;


public class AllTests {
    public static void main(String[] args) {
        new BulkTestRunner().run(
            new EntityClassTest(),
            new EntityFieldTest(),
            new EntityClassFactoryTest(),
            new EntityFieldFactoryTest(),
            new EntityClassRepositoryTest(),
            new EntityClassRepositoryBuilderTest()
        );
    }
}
