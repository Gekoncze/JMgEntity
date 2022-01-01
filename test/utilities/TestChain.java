package utilities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;
import cz.mg.entity.validator.common.Required;


public @Entity class TestChain {
    public static EntityClass entity;

    public @Required @Value String name;
    public @Link TestChain next;
}
