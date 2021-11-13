package cz.mg.entity.mapper.mappers.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.mapper.mappers.ValueObjectMapper;


public class BooleanObjectMapper implements ValueObjectMapper<Boolean> {
    @Override
    public @Mandatory String getName() {
        return Boolean.class.getSimpleName();
    }

    @Override
    public Boolean create(@Optional String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public @Optional String getValue(@Mandatory Boolean object) {
        return Boolean.toString(object);
    }
}
