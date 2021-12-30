package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.validator.common.Required;


@Deprecated
public @Entity class TestEntity {
    public static EntityClass entity;

    public @Required @Value Integer number;
    public @Value TestEnum enumeration;
    public @Link TestEntity next;
}
