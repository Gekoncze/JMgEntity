package cz.mg.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.collections.list.List;


public @Utility class EntityClassModule extends List<Class> {
    public EntityClassModule(Class... array) {
        super(array);
    }
}
