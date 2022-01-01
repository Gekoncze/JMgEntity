package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


@Deprecated
public @Entity class TestBranch {
    public @Link TestTree parent;
    public @Value TestEnum leaf;
}
