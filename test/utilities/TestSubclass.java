package utilities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.EntityClass;


public @Entity class TestSubclass extends TestLeaf {
    public static EntityClass entity;

    public @Link TestSubclass next;
}
