package cz.mg.entity.explorer.gui.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.map.Map;


public @Utility class Navigation {
    private final @Mandatory @Link Map<Object, NavigationNode> map;
    private final @Optional @Part NavigationNode root;

    public Navigation(@Mandatory Map<Object, NavigationNode> map, @Optional NavigationNode root) {
        this.map = map;
        this.root = root;
    }

    public @Optional NavigationNode getRoot() {
        return root;
    }

    public @Optional NavigationNode get(@Optional Object object){
        if(object != null){
            return map.get(object);
        } else {
            return null;
        }
    }
}
