package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Entity class Node {
    public @Value String name;
    public @Value String value;
    public @Value List<Integer> fields;
}
