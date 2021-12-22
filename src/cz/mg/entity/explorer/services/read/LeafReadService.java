package cz.mg.entity.explorer.services.read;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;


public @Service class LeafReadService implements ObjectReadService {
    @Override
    public int count(@Optional Object object) {
        return 0;
    }

    @Override
    public @Optional Object read(@Optional Object object, int i) {
        throw new ArrayIndexOutOfBoundsException(i + " out of 0");
    }

    @Override
    public @Mandatory ReadableList<Object> read(@Optional Object object) {
        return new List<>();
    }
}
