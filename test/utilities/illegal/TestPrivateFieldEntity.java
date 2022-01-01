package utilities.illegal;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class TestPrivateFieldEntity {
    public static EntityClass entity;

    private @Value Integer integerValue;
}
