package cz.mg.entity.explorer.gui.components.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.services.ClassIconProvider;

import java.awt.*;

import static cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class EntityClassPopupMenu extends UiPopupMenu {
    private final @Mandatory @Link EntityClass entityClass;

    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();

    public EntityClassPopupMenu(
        @Mandatory ExplorerWindow window,
        @Mandatory EntityClass entityClass,
        @Mandatory SelectEventHandler<EntityClass> selectEventHandler
    ) {
        this.entityClass = entityClass;

        for(EntityClass option : entityClass.getSubclasses()){
            add(new UiValueMenuItem(
                window,
                classIconProvider.get(window.getGallery(), option.getClazz()),
                option,
                option.getName(),
                selectEventHandler
            ));
        }
    }

    public void select(@Mandatory Component component){
        if(entityClass.getSubclasses().count() == 1){
            getMenuItem(0).doClick();
        } else if(entityClass.getSubclasses().count() > 1) {
            show(component);
        }
    }
}
