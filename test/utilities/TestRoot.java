package utilities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.validator.common.Required;


public @Entity class TestRoot {
    public static EntityClass entity;

    public @Required @Value String name;
    public @Part List<TestBranch> branches = new List<>();
}
