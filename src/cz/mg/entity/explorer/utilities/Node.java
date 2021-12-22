package cz.mg.entity.explorer.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.services.ReadService;


public @Utility class Node {
    private final @Mandatory @Shared ReadService readService = new ReadService();

    private final @Mandatory Explorer explorer;
    private final @Optional @Link Node parentNode;
    private final @Optional @Link Object object;
    private final @Value int index;

    public Node(@Mandatory Explorer explorer, @Optional Node parentNode, @Optional Object object, int index) {
        this.explorer = explorer;
        this.parentNode = parentNode;
        this.object = object;
        this.index = index;
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    public @Optional Node getParentNode() {
        return parentNode;
    }

    public @Optional Object getObject() {
        return object;
    }

    public int getIndex() {
        return index;
    }

    public @Mandatory ReadableList<Node> getChildNodes(){
        List<Node> nodes = new List<>();
        int i = 0;
        for(Object child : readService.read(object)){
            nodes.addLast(new Node(explorer, this, child, i));
            i++;
        }
        return nodes;
    }
}
