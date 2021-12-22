package cz.mg.entity.explorer.services.read;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;


public @Service class EntityReadService implements ObjectReadService {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    @Override
    public int count(@Optional Object object) {
        if(object != null){
            return entityClassProvider
                .get(object.getClass())
                .getFields()
                .count();
        } else {
            return 0;
        }
    }

    @Override
    public @Optional Object read(@Optional Object object, int i){
        if(object != null){
            EntityClass entityClass = entityClassProvider.get(object.getClass());
            if(i >= 0 && i < entityClass.getFields().count()){
                EntityField entityField = entityClass.getFields().get(i);
                return entityField.get(object);
            } else {
                throw new ArrayIndexOutOfBoundsException(i + " out of " + entityClass.getFields().count());
            }
        } else {
            throw new ArrayIndexOutOfBoundsException(i + " out of 0");
        }
    }

    @Override
    public @Mandatory ReadableList<Object> read(@Optional Object object) {
        if(object != null){
            EntityClass entityClass = entityClassProvider.get(object.getClass());
            List<Object> objects = new List<>();
            for(EntityField field : entityClass.getFields()){
                objects.addLast(field.get(object));
            }
            return objects;
        } else {
            return new List<>();
        }
    }
}
