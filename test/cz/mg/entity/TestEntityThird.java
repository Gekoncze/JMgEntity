package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


@Deprecated
public @Entity class TestEntityThird {
    public static EntityClass entity;

    public @Part TestEntityChild testEntityChild;
    public @Value Boolean bool;
}
