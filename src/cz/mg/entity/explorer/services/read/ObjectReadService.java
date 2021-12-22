package cz.mg.entity.explorer.services.read;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.ReadableList;


public @Service interface ObjectReadService {
    int count(@Optional Object object);
    @Optional Object read(@Optional Object object, int i);
    @Mandatory ReadableList<Object> read(@Optional Object object);
}
