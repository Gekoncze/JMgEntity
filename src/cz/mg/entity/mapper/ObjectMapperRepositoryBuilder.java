package cz.mg.entity.mapper;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.mapper.common.EntityObjectMapper;
import cz.mg.entity.mapper.common.EnumObjectMapper;
import cz.mg.entity.mapper.common.collection.ListObjectMapper;
import cz.mg.entity.mapper.common.value.BooleanObjectMapper;
import cz.mg.entity.mapper.common.value.IntegerObjectMapper;
import cz.mg.entity.mapper.common.value.StringObjectMapper;


public @Utility class ObjectMapperRepositoryBuilder {
    private final @Mandatory @Part Map<@Link Class, @Part ObjectMapper> map = new Map<>();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public ObjectMapperRepositoryBuilder() {
    }

    public <T> @Mandatory ObjectMapperRepositoryBuilder addObjectMapper(
        @Mandatory Class<T> clazz,
        @Mandatory ObjectMapper<T> objectMapper
    ){
        map.set(clazz, objectMapper);
        return this;
    }

    public @Mandatory ObjectMapperRepositoryBuilder addDefault(){
        addObjectMapper(Boolean.class, new BooleanObjectMapper());
        addObjectMapper(Integer.class, new IntegerObjectMapper());
        addObjectMapper(String.class, new StringObjectMapper());
        addObjectMapper(List.class, new ListObjectMapper());
        return this;
    }

    public @Mandatory ObjectMapperRepositoryBuilder addEntity(@Mandatory Class entityClass){
        addObjectMapper(entityClass, new EntityObjectMapper(entityClassProvider.get(entityClass)));
        return this;
    }

    public @Mandatory ObjectMapperRepositoryBuilder addEnum(@Mandatory Class enumClass){
        addObjectMapper(enumClass, new EnumObjectMapper(enumClass));
        return this;
    }

    public @Mandatory ObjectMapperRepositoryBuilder addClassOptional(@Mandatory Class clazz){
        if(Enum.class.isAssignableFrom(clazz)){
            addEnum(clazz);
        } else if(clazz.isAnnotationPresent(Entity.class)){
            addEntity(clazz);
        }
        return this;
    }

    public @Mandatory ObjectMapperRepository build(){
        ObjectMapperRepository repository = new ObjectMapperRepository();
        for(Class clazz : map.keys()){
            repository.set(clazz, map.get(clazz));
        }
        return repository;
    }
}
