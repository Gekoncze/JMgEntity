package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public @Entity class TestEntitySecond extends TestEntity {
    public @Value String text;
}
