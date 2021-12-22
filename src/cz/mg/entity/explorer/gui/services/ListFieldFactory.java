package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.fields.LinkField;
import cz.mg.entity.explorer.gui.components.fields.ObjectField;
import cz.mg.entity.explorer.gui.components.fields.PartField;
import cz.mg.entity.explorer.gui.components.fields.value.BooleanValueField;
import cz.mg.entity.explorer.gui.components.fields.value.EnumValueField;
import cz.mg.entity.explorer.gui.components.fields.value.IntegerValueField;
import cz.mg.entity.explorer.gui.components.fields.value.StringValueField;

import java.lang.annotation.Annotation;


public @Service class ListFieldFactory {
    public @Mandatory ObjectField create(
        @Mandatory ExplorerWindow window,
        @Mandatory List list,
        int index,
        @Mandatory Class type,
        @Mandatory Class<? extends Annotation> ownership
    ){
        String label = "" + index;

        if(isList(type)){
            throw new UnsupportedOperationException("Nested lists are not supported.");
        }

        if(isLink(ownership)){
            return new LinkField(window, list, index, type, label);
        }

        if(isPart(ownership)){
            return new PartField(window, list, index, type, label);
        }

        if(isValue(ownership)){
            if(isString(type)){
                return new StringValueField(window, list, index, type, label);
            }

            if(isInteger(type)){
                return new IntegerValueField(window, list, index, type, label);
            }

            if(isBoolean(type)){
                return new BooleanValueField(window, list, index, type, label);
            }

            if(isEnum(type)){
                return new EnumValueField(window, list, index, type, label);
            }
        }

        throw new UnsupportedOperationException("Unsupported object type '" + type.getSimpleName() + "'.");
    }
    
    private boolean isLink(@Mandatory Class<? extends Annotation> annotation){
        return is(annotation, Link.class);
    }

    private boolean isPart(@Mandatory Class<? extends Annotation> annotation){
        return is(annotation, Part.class);
    }

    private boolean isValue(@Mandatory Class<? extends Annotation> annotation){
        return is(annotation, Value.class);
    }
    
    private boolean is(@Mandatory Class actualClass, @Mandatory Class expectedClass){
        return expectedClass.isAssignableFrom(actualClass);
    }

    private boolean isList(@Mandatory Class clazz){
        return is(clazz, List.class);
    }

    private boolean isString(@Mandatory Class clazz){
        return is(clazz, String.class);
    }

    private boolean isInteger(@Mandatory Class clazz){
        return is(clazz, Integer.class);
    }

    private boolean isBoolean(@Mandatory Class clazz){
        return is(clazz, Boolean.class);
    }

    private boolean isEnum(@Mandatory Class clazz){
        return is(clazz, Enum.class);
    }
}
