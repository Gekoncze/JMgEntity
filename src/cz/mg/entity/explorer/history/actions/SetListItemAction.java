package cz.mg.entity.explorer.history.actions;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.history.Action;


public @Utility class SetListItemAction implements Action {
    private final @Mandatory @Link List list;
    private final @Value int index;
    private final @Optional @Link Object oldItem;
    private final @Optional @Link Object newItem;

    public SetListItemAction(@Mandatory List list, int index, @Optional Object oldItem, @Optional Object newItem) {
        this.list = list;
        this.index = index;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    @Override
    public void redo() {
        list.set(newItem, index);
    }

    @Override
    public void undo() {
        list.set(oldItem, index);
    }
}
