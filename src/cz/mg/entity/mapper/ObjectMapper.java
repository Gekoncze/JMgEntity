package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;


public @Utility interface ObjectMapper<T> {
    public @Mandatory String getName();
    public @Mandatory T create(@Optional String value);
    public @Optional String getValue(@Mandatory T object);
    public @Mandatory List<Object> getFields(@Mandatory T object);
    public void setFields(@Mandatory T object, @Mandatory List<Object> fields);
}
