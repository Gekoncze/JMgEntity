package cz.mg.entity.explorer.gui.ui.controls.field.base;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.services.ObjectNameProvider;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;


public @Utility class UiObjectFieldBase extends UiFieldBase {
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();

    public UiObjectFieldBase(@Mandatory ExplorerWindow window) {
    }

    @Override
    public @Optional Object getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(@Optional Object value) {
        if(value != null){
            setNull(false);
            setText(objectNameProvider.get(value));
        } else {
            setNull(true);
            setText("");
        }
    }
}
