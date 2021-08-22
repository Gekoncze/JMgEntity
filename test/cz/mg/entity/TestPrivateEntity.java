package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public @Entity class TestPrivateEntity {
    private @Value Integer number;
    private @Value TestEnum enumeration;
    private @Link TestPrivateEntity next;
}
