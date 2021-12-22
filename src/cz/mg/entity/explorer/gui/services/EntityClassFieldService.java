package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityField;


public @Service class EntityClassFieldService {
    public int getIndexOf(@Mandatory EntityClass targetClass, @Mandatory EntityField targetField){
        int i = 0;
        for(EntityField entityField : targetClass.getFields()){
            if(entityField == targetField){
                return i;
            }
            i++;
        }

        throw new IllegalArgumentException(
            "Could not find field '" + targetField.getName() + "' in class '" + targetClass.getName() + "'."
        );
    }
}
