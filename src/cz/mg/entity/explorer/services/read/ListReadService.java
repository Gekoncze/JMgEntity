package cz.mg.entity.explorer.services.read;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;


public @Service class ListReadService implements ObjectReadService {
    @Override
    public int count(@Optional Object object) {
        if(object != null){
            return ((List) object).count();
        } else {
            return 0;
        }
    }

    @Override
    public @Optional Object read(@Optional Object object, int i){
        if(object != null){
            List list = (List) object;
            if(i >= 0 && i < list.count()){
                return list.get(i);
            } else {
                throw new ArrayIndexOutOfBoundsException(i + " out of " + list.count());
            }
        } else {
            throw new ArrayIndexOutOfBoundsException(i + "out of 0");
        }
    }

    @Override
    public @Mandatory ReadableList<Object> read(@Optional Object object) {
        if(object != null){
            return (List<Object>) object;
        } else {
            return new List<>();
        }
    }
}
