package cz.mg.entity;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public @Entity class TestEntityThird {
    public @Part TestEntitySecond testEntitySecond;
    public @Value Boolean bool;
}
