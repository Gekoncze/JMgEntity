package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.collections.set.Set;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.utilities.Node;


public @Service class SearchService {
    public @Mandatory List<Node> findUsages(@Mandatory Explorer explorer, @Mandatory Object target){
        List<Node> results = new List<>();
        forEach(explorer, node -> {
            for(Node childNode : node.getChildNodes()){
                if(childNode.getObject() == target){
                    results.addLast(childNode);
                }
            }
        });
        return results;
    }

    public void forEach(@Mandatory Explorer explorer, @Mandatory Visitor visitor){
        forEach(createRootNode(explorer), visitor, new Set<>());
    }

    private void forEach(@Mandatory Node node, @Mandatory Visitor visitor, @Mandatory Set<Object> visited){
        Object current = node.getObject();
        if(current != null){
            if(!visited.contains(current)){
                visited.set(current);
                visitor.visit(node);
                for(Node childNode : node.getChildNodes()){
                    forEach(childNode, visitor, visited);
                }
            }
        }
    }

    private @Mandatory Node createRootNode(@Mandatory Explorer explorer){
        return new Node(explorer, null, explorer.getRoot(), -1);
    }

    public interface Visitor {
        void visit(@Mandatory Node node);
    }
}
