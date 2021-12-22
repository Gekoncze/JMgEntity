package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.history.ActionFactory;


public @Service class UpdateService {
    private final @Mandatory @Shared ActionFactory actionFactory = new ActionFactory();
    private final @Mandatory @Shared ReadService readService = new ReadService();

    public void update(@Mandatory Explorer explorer, @Optional Object object, int i, @Optional Object value){
        int count = readService.count(object);
        if(i >= 0 && i < count){
            explorer.getTransactionManager().run(
                actionFactory.createSetAction(object, i, value)
            );
        } else {
            throw new ArrayIndexOutOfBoundsException(i + " out of " + count);
        }
    }
}
