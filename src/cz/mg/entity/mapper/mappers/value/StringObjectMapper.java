package cz.mg.entity.mapper.mappers.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.mapper.mappers.ValueObjectMapper;


public class StringObjectMapper implements ValueObjectMapper<String> {
    @Override
    public @Mandatory String getName() {
        return String.class.getSimpleName();
    }

    @Override
    public String create(@Optional String value) {
        return value;
    }

    @Override
    public @Optional String getValue(@Mandatory String object) {
        return object;
    }
}
