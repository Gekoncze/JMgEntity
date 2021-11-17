package cz.mg.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;


public @Utility class EntityClass {
    final @Mandatory Class clazz;
    final @Mandatory List<EntityField> fields;
    final @Mandatory List<EntityClass> subclasses;

    EntityClass(
        @Mandatory Class clazz,
        @Mandatory List<EntityField> fields,
        @Mandatory List<EntityClass> subclasses
    ) {
        this.clazz = clazz;
        this.fields = fields;
        this.subclasses = subclasses;
    }

    public @Mandatory Class getClazz() {
        return clazz;
    }

    public @Mandatory ReadableList<EntityField> getFields() {
        return fields;
    }

    public @Mandatory ReadableList<EntityClass> getSubclasses() {
        return subclasses;
    }

    public @Optional EntityField getField(@Mandatory String name){
        for(EntityField field : fields){
            if(field.getName().equals(name)){
                return field;
            }
        }
        return null;
    }

    public @Mandatory Object newInstance(){
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    public @Mandatory String getName(){
        return clazz.getSimpleName();
    }
}
