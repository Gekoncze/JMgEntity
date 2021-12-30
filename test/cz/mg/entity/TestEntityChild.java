package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


@Deprecated
public @Entity class TestEntityChild extends TestEntity {
    public static EntityClass entity;

    public @Value String text;
}
