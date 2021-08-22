package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public @Entity class TestEntity {
    public @Value Integer number;
    public @Value TestEnum enumeration;
    public @Link TestEntity next;
}
