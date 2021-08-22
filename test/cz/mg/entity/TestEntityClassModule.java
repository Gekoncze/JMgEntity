package cz.mg.entity;

import cz.mg.annotations.classes.Utility;


public @Utility class TestEntityClassModule extends EntityClassModule {
    public TestEntityClassModule() {
        super(
            TestEntity.class,
            TestEntitySecond.class,
            TestEntityThird.class
        );
    }
}
