package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.Entities;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.utilities.Node;


public @Service class OwnershipService {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared SearchService searchService = new SearchService();

    public boolean hasOwner(@Mandatory Explorer explorer, @Mandatory Object target){
        return ownerCount(explorer, target) > 0;
    }

    public int ownerCount(@Mandatory Explorer explorer, @Mandatory Object target){
        int count = 0;
        List<Node> usages = searchService.findUsages(explorer, target);
        for(Node usage : usages){
            if(isOwnedByParent(usage)){
                count++;
            }
        }
        return count;
    }

    public boolean isOwnedByParent(@Mandatory Node node){
        Node parent = node.getParentNode();
        if(parent != null){
            if(parent.getObject() instanceof List){
                // list items inherit ownership from parent
                return isOwnedByParent(parent);
            }

            if(Entities.isEntity(parent.getObject())){
                EntityField field = entityClassProvider
                    .get(parent.getObject().getClass())
                    .getFields()
                    .get(node.getIndex());

                return field.isAnnotationPresent(Part.class) || field.isAnnotationPresent(Shared.class);
            }

            throw new IllegalStateException("Leaf node cannot be a parent of another node.");
        } else {
            // root object is owned by default
            return true;
        }
    }
}
