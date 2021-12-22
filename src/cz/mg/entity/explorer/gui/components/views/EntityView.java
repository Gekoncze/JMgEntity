package cz.mg.entity.explorer.gui.components.views;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.other.Refreshable;
import cz.mg.entity.explorer.gui.services.EntityFieldFactory;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.controls.UiPanel;

import java.awt.*;
import java.awt.event.KeyEvent;


public @Utility class EntityView extends UiPanel implements ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;

    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared EntityFieldFactory entityFieldFactory = new EntityFieldFactory();

    private final @Mandatory @Link ExplorerWindow window;
    private final @Mandatory @Link Object entity;

    public EntityView(@Mandatory ExplorerWindow window, @Mandatory Object entity) {
        super(BORDER, PADDING, UiAlignment.TOP);

        this.window = window;
        this.entity = entity;

        EntityClass entityClass = entityClassProvider.get(entity.getClass());
        for(EntityField entityField : entityClass.getFields()){
            addVertical(
                entityFieldFactory.create(window, entity, entityClass, entityField),
                1, 0, UiAlignment.MIDDLE, UiFill.BOTH
            );
        }
        rebuild();
    }

    @Override
    public void refresh() {
        for(Component component : getComponents()){
            if(component instanceof Refreshable){
                ((Refreshable) component).refresh();
            }
        }
    }

    @Override
    public Object getObject() {
        return entity;
    }

    @Override
    public void onKeyEvent(KeyEvent e) {
    }
}
