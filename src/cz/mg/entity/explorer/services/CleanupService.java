package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.map.Map;
import cz.mg.collections.map.Pair;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.utilities.Node;


public @Service class CleanupService {
    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();

    public void cleanup(@Mandatory Explorer explorer){
        deleteOrphans(explorer, getObjectOwnershipMap(explorer));
    }

    private Map<Object, Integer> getObjectOwnershipMap(@Mandatory Explorer explorer){
        Map<Object, Integer> map = new Map<>();
        searchService.forEach(explorer, node -> {
            map.get(node.getObject(), 0);
            for(Node child : node.getChildNodes()){
                if(child.getObject() != null){
                    map.get(child.getObject(), 0);
                    if(ownershipService.isOwnedByParent(child)){
                        map.set(child.getObject(), map.get(child.getObject(), 0) + 1);
                    }
                }
            }
        });
        return map;
    }

    private void deleteOrphans(@Mandatory Explorer explorer, Map<Object, Integer> map){
        for(Pair<Object, Integer> pair : map.pairs()){
            if(pair.getValue() < 1){
                deleteService.delete(explorer, pair.getValue());
            }
        }
    }
}
