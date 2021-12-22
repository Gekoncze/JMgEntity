package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.utilities.Navigation;
import cz.mg.entity.explorer.gui.utilities.NavigationNode;


public @Service class EntitySearch {
    private static final int SEARCH_RESULTS_LIMIT = 10;
    private static final int SEARCH_NAME_LIMIT = 1000;

    private final @Mandatory @Shared EntityNameProvider entityNameProvider = new EntityNameProvider();

    public @Mandatory List<Object> search(
        @Mandatory Navigation navigation,
        @Mandatory Class typeFilter,
        @Mandatory String nameFilter
    ){
        List<Object> results = new List<>();
        search(
            results,
            typeFilter,
            nameFilter,
            navigation.getRoot()
        );
        return results;
    }

    private void search(
        @Mandatory List<Object> results,
        @Mandatory Class typeFilter,
        @Mandatory String nameFilter,
        @Optional NavigationNode node
    ) {
        if(results.count() < SEARCH_RESULTS_LIMIT){
            if(node != null){
                Object object = node.getSelf();
                if(typeFilter.isAssignableFrom(object.getClass())){
                    String name = entityNameProvider.get(object);
                    if(name != null) {
                        if(matches(nameFilter, name)){
                            results.addLast(object);
                        }
                    }
                }

                for(NavigationNode child : node.getChildren()){
                    search(results, typeFilter, nameFilter, child);
                }
            }
        }
    }

    private boolean matches(@Mandatory String expectations, @Mandatory String reality){
        if(expectations.length() < 1){
            return true;
        }

        expectations = limit(expectations.toLowerCase(), SEARCH_NAME_LIMIT);
        reality = limit(reality.toLowerCase(), SEARCH_NAME_LIMIT);

        return reality.contains(expectations);
    }

    private @Mandatory String limit(@Mandatory String s, int limit){
        if(s.length() <= limit){
            return s;
        } else {
            return s.substring(0, limit);
        }
    }
}
