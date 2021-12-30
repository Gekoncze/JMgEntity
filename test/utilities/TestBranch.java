package utilities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class TestBranch {
    public static EntityClass entity;

    public @Part List<TestLeaf> leaves = new List<>();
}
