package cz.mg.entity.explorer.history;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.Entities;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.entity.explorer.history.actions.SetEntityFieldAction;
import cz.mg.entity.explorer.history.actions.SetListItemAction;


public @Service class ActionFactory {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public @Mandatory Action createSetAction(@Optional Object object, int i, @Optional Object value){
        if(object instanceof List){
            return createSetListItemAction(object, i, value);
        }

        if(Entities.isEntity(object)) {
            return createSetEntityFieldAction(object, i, value);
        }

        throw new UnsupportedOperationException("Cannot create set action for leaf object.");
    }

    private @Mandatory Action createSetListItemAction(@Mandatory Object object, int i, @Optional Object value){
        List list = (List) object;
        Object target = list.get(i);
        return new SetListItemAction(list, i, target, value);
    }

    private @Mandatory Action createSetEntityFieldAction(@Mandatory Object object, int i, @Optional Object value){
        EntityClass entityClass = entityClassProvider.get(object.getClass());
        EntityField entityField = entityClass.getFields().get(i);
        Object target = entityField.get(object);
        return new SetEntityFieldAction(object, entityField, target, value);
    }
}
