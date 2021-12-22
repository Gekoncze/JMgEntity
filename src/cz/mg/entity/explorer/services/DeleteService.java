package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.utilities.Node;


public @Service class DeleteService {
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();
    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();

    public void delete(@Mandatory Explorer explorer, @Mandatory Object target){
        List<Node> usages = searchService.findUsages(explorer, target);
        for(Node usage : usages){
            Node parentNode = usage.getParentNode();
            Object parent = parentNode != null ? parentNode.getObject() : null;
            if(parent != null){
                updateService.update(explorer, parent, usage.getIndex(), null);
            } else {
                throw new UnsupportedOperationException("Cannot delete root object.");
            }
        }

        removeChildren(explorer, target);
    }

    private void removeChildren(@Mandatory Explorer explorer, @Mandatory Object target){
        for(int i = 0; i < readService.count(target); i++){
            Object targetChild = readService.read(target, i);
            if(targetChild != null){
                if(!ownershipService.hasOwner(explorer, targetChild)){
                    delete(explorer, targetChild);
                }
            }
        }
    }
}
