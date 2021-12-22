package cz.mg.entity.explorer.history.actions;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.EntityField;
import cz.mg.entity.explorer.history.Action;


public @Utility class SetEntityFieldAction implements Action {
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;
    private final @Optional @Link Object oldValue;
    private final @Optional @Link Object newValue;

    public SetEntityFieldAction(
        @Optional Object entity,
        @Optional EntityField entityField,
        @Optional Object oldValue,
        @Optional Object newValue
    ) {
        this.entityField = entityField;
        this.entity = entity;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void redo() {
        entityField.set(entity, newValue);
    }

    @Override
    public void undo() {
        entityField.set(entity, oldValue);
    }
}
