package cz.mg.entity.explorer.gui.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Utility class NavigationNode {
    private final @Optional @Link NavigationNode parent;
    private final @Mandatory @Link Object self;
    private final @Mandatory @Part List<NavigationNode> children = new List<>();
    private final @Mandatory @Value String label;

    public NavigationNode(@Optional NavigationNode parent, @Mandatory Object self, @Mandatory String label) {
        this.parent = parent;
        this.self = self;
        this.label = label;
    }

    public @Optional NavigationNode getParent() {
        return parent;
    }

    public @Mandatory Object getSelf() {
        return self;
    }

    public @Mandatory List<NavigationNode> getChildren() {
        return children;
    }

    public @Mandatory String getLabel() {
        return label;
    }
}
