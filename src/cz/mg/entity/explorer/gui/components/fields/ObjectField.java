package cz.mg.entity.explorer.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.explorer.gui.services.ObjectIconProvider;
import cz.mg.entity.explorer.gui.ui.controls.UiPanel;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.services.ReadService;
import cz.mg.entity.explorer.services.UpdateService;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.other.Refreshable;

import java.awt.*;


public abstract @Utility class ObjectField extends UiPanel implements Refreshable {
    private static final int BORDER = 0;
    private static final int PADDING = 2;

    private final @Mandatory @Shared ObjectIconProvider objectIconProvider = new ObjectIconProvider();
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();

    private final @Mandatory @Link ExplorerWindow window;
    private final @Mandatory @Link Object object;
    private final @Value int index;
    private final @Mandatory @Link Class type;

    protected ObjectField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type
    ) {
        super(BORDER, PADDING, UiAlignment.MIDDLE);
        this.window = window;
        this.object = object;
        this.index = index;
        this.type = type;
    }

    public @Mandatory ExplorerWindow getWindow() {
        return window;
    }

    public @Mandatory Class getType() {
        return type;
    }

    public final @Optional Object getValue(){
        return readService.read(object, index);
    }

    public void setValue(@Optional Object value) {
        updateService.update(window.getExplorer(), object, index, value);
        refresh();
    }

    protected @Optional Image getIcon(@Optional Object object){
        return objectIconProvider.get(window.getGallery(), object);
    }
}
