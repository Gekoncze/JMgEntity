package cz.mg.entity.explorer.gui.components.popups;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.services.EntityNameProvider;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiDummyMenuItem;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem;
import cz.mg.entity.explorer.gui.services.ObjectIconProvider;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.utilities.Navigation;
import cz.mg.entity.explorer.gui.utilities.NavigationNode;
import cz.mg.entity.explorer.gui.services.EntitySearch;

import java.awt.*;

import static cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class EntitySearchPopupMenu extends UiPopupMenu {
    private final @Mandatory @Shared ObjectIconProvider objectIconProvider = new ObjectIconProvider();
    private final @Mandatory @Shared EntityNameProvider entityNameProvider = new EntityNameProvider();

    private final @Mandatory @Shared ExplorerWindow window;
    private final @Mandatory @Part SelectEventHandler<Object> selectEventHandler;

    public EntitySearchPopupMenu(
        @Mandatory ExplorerWindow window,
        @Mandatory SelectEventHandler<Object> selectEventHandler
    ) {
        this.window = window;
        this.selectEventHandler = selectEventHandler;
    }

    public void search(
        @Mandatory Component component,
        @Mandatory Class classFilter,
        @Mandatory String nameFilter,
        @Mandatory Navigation navigation
    ) {
        removeAll();

        List<Object> results = new EntitySearch().search(navigation, classFilter, nameFilter);

        for(Object result : results){
            add(
                new UiValueMenuItem<>(
                    window,
                    objectIconProvider.get(window.getGallery(), result),
                    result,
                    findComponentPath(navigation, result),
                    selectEventHandler
                )
            );
        }

        if(results.count() < 1){
            add(new UiDummyMenuItem("No results."));
        }

        show(component, 0, component.getHeight());
    }

    private @Mandatory String findComponentPath(
        @Mandatory Navigation navigation,
        @Mandatory Object object
    ){
        List<Object> path = new List<>();
        NavigationNode current = navigation.get(object);
        while(current != null){
            if(current.getSelf().getClass().isAnnotationPresent(Entity.class)){
                path.addFirst(current.getSelf());
            }
            current = current.getParent();
        }
        path.removeFirst(); // remove root location
        return new ToStringBuilder<>(path).convert(entityNameProvider::get).delim(".").build();
    }
}
