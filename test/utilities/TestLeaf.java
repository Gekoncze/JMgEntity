package utilities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class TestLeaf {
    public static EntityClass entity;

    public @Value Integer integerValue;
    public @Value Long longValue;
    public @Value Float floatValue;
    public @Value Double doubleValue;
    public @Value Boolean booleanValue;
    public @Value Byte byteValue;
    public @Value Character characterValue;
    public @Value String stringValue;
    public @Value TestEnum enumValue;
}
