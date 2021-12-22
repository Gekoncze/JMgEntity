package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.entity.Entities;
import cz.mg.entity.explorer.services.read.EntityReadService;
import cz.mg.entity.explorer.services.read.LeafReadService;
import cz.mg.entity.explorer.services.read.ListReadService;
import cz.mg.entity.explorer.services.read.ObjectReadService;


public @Service class ReadService {
    private final @Mandatory @Shared ListReadService listReadService = new ListReadService();
    private final @Mandatory @Shared EntityReadService entityReadService = new EntityReadService();
    private final @Mandatory @Shared LeafReadService leafReadService = new LeafReadService();

    public int count(@Optional Object object){
        return get(object).count(object);
    }

    public @Optional Object read(@Optional Object object, int i){
        return get(object).read(object, i);
    }

    public @Mandatory ReadableList<Object> read(@Optional Object object){
        return get(object).read(object);
    }

    private @Mandatory ObjectReadService get(@Optional Object object){
        if(object instanceof List){
            return listReadService;
        }

        if(Entities.isEntity(object)) {
            return entityReadService;
        }

        return leafReadService;
    }
}
