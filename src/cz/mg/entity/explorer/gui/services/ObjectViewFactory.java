package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.views.EntityView;
import cz.mg.entity.explorer.gui.components.views.ObjectView;
import cz.mg.entity.explorer.gui.components.views.ScrollingObjectView;


public @Service class ObjectViewFactory {
    public @Mandatory ObjectView create(@Mandatory ExplorerWindow window, @Mandatory Object object){
        if(object.getClass().isAnnotationPresent(Entity.class)){
            return new ScrollingObjectView(new EntityView(window, object));
        }

        // TODO - add support for more object types
        throw new UnsupportedOperationException("Unsupported object of type '" + object.getClass().getSimpleName() + "'.");
    }
}
