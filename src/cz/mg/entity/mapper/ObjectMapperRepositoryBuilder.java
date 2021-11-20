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

    public <T> void addObjectMapper(@Mandatory Class<T> clazz, @Mandatory ObjectMapper<T> objectMapper){
        map.set(clazz, objectMapper);
    }

    public void addDefault(){
        addObjectMapper(Boolean.class, new BooleanObjectMapper());
        addObjectMapper(Integer.class, new IntegerObjectMapper());
        addObjectMapper(String.class, new StringObjectMapper());
        addObjectMapper(List.class, new ListObjectMapper());
    }

    public void addEntity(@Mandatory Class entityClass){
        addObjectMapper(entityClass, new EntityObjectMapper(entityClassProvider.get(entityClass)));
    }

    public void addEnum(@Mandatory Class enumClass){
        addObjectMapper(enumClass, new EnumObjectMapper(enumClass));
    }

    public void addClassOptional(@Mandatory Class clazz){
        if(Enum.class.isAssignableFrom(clazz)){
            addEnum(clazz);
        } else if(clazz.isAnnotationPresent(Entity.class)){
            addEntity(clazz);
        }
    }

    public ObjectMapperRepository build(){
        ObjectMapperRepository repository = new ObjectMapperRepository();
        for(Class clazz : map.keys()){
            repository.set(clazz, map.get(clazz));
        }
        return repository;
    }
}
