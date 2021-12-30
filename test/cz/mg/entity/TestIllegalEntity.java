package cz.mg.entity;

import cz.mg.annotations.classes.Entity;


@Deprecated
public @Entity class TestIllegalEntity {
    public Integer number;
    public TestEnum enumeration;
    public TestIllegalEntity next;
}
