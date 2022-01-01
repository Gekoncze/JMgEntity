package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


@Deprecated
public @Entity class TestTree {
    public @Value Integer height;
    public @Part List<TestBranch> branches;
}
