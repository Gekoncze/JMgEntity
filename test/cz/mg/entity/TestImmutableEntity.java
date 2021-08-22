package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public @Entity class TestImmutableEntity {
    public final @Value Integer number;
    public final @Value TestEnum enumeration;
    public final @Link TestEntity next;

    public TestImmutableEntity(Integer number, TestEnum enumeration, TestEntity next) {
        this.number = number;
        this.enumeration = enumeration;
        this.next = next;
    }
}
