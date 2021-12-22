package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityField;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.fields.LinkField;
import cz.mg.entity.explorer.gui.components.fields.ListField;
import cz.mg.entity.explorer.gui.components.fields.ObjectField;
import cz.mg.entity.explorer.gui.components.fields.PartField;
import cz.mg.entity.explorer.gui.components.fields.value.BooleanValueField;
import cz.mg.entity.explorer.gui.components.fields.value.EnumValueField;
import cz.mg.entity.explorer.gui.components.fields.value.IntegerValueField;
import cz.mg.entity.explorer.gui.components.fields.value.StringValueField;

import java.lang.annotation.Annotation;


public @Service class EntityFieldFactory {
    private final @Mandatory @Shared EntityClassFieldService entityClassFieldService = new EntityClassFieldService();
    private final @Mandatory @Shared CollectionTypeProvider collectionTypeProvider = new CollectionTypeProvider();

    public @Mandatory ObjectField create(
        @Mandatory ExplorerWindow window,
        @Mandatory Object entity,
        @Mandatory EntityClass entityClass,
        @Mandatory EntityField entityField
    ){
        int index = entityClassFieldService.getIndexOf(entityClass, entityField);
        Class type = entityField.getType();
        String label = entityField.getName();

        if(isList(entityField)){
            return new ListField(
                window, entity, index, type, label,
                collectionTypeProvider.get(entityField.getField()),
                getOwnership(entityField)
            );
        }

        if(isLink(entityField)){
            return new LinkField(window, entity, index, type, label);
        }

        if(isPart(entityField)){
            return new PartField(window, entity, index, type, label);
        }

        if(isValue(entityField)){
            if(isString(entityField)){
                return new StringValueField(window, entity, index, type, label);
            }

            if(isInteger(entityField)){
                return new IntegerValueField(window, entity, index, type, label);
            }

            if(isBoolean(entityField)){
                return new BooleanValueField(window, entity, index, type, label);
            }

            if(isEnum(entityField)){
                return new EnumValueField(window, entity, index, type, label);
            }
        }

        throw new UnsupportedOperationException("Unsupported object type '" + type.getSimpleName() + "'.");
    }

    private boolean isLink(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Link.class);
    }

    private boolean isPart(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Part.class);
    }

    private boolean isValue(@Mandatory EntityField entityField){
        return entityField.isAnnotationPresent(Value.class);
    }

    private boolean is(@Mandatory EntityField entityField, @Mandatory Class clazz){
        return clazz.isAssignableFrom(entityField.getField().getType());
    }

    private boolean isList(@Mandatory EntityField entityField){
        return is(entityField, List.class);
    }

    private boolean isString(@Mandatory EntityField entityField){
        return is(entityField, String.class);
    }

    private boolean isInteger(@Mandatory EntityField entityField){
        return is(entityField, Integer.class);
    }

    private boolean isBoolean(@Mandatory EntityField entityField){
        return is(entityField, Boolean.class);
    }

    private boolean isEnum(@Mandatory EntityField entityField){
        return is(entityField, Enum.class);
    }

    private @Mandatory Class<? extends Annotation> getOwnership(@Mandatory EntityField entityField){
        if(isLink(entityField)){
            return Link.class;
        }

        if(isPart(entityField)){
            return Part.class;
        }

        if(isValue(entityField)){
            return Value.class;
        }

        throw new UnsupportedOperationException("Unknown ownership.");
    }
}
