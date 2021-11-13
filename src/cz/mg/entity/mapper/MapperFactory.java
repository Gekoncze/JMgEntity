package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Service class MapperFactory {
    public <T> @Mandatory Mapper<T> create(@Mandatory List<Class> classes){
        ObjectMapperRepositoryBuilder builder = new ObjectMapperRepositoryBuilder();
        builder.addDefault();
        for(Class clazz : classes){
            builder.addClassOptional(clazz);
        }
        return new Mapper<>(builder.build());
    }
}
