package cz.mg.entity.validator;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.map.Map;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;


public @Service class EntityGraphValidator {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();
    private final @Mandatory @Shared EntityValidator entityValidator = new EntityValidator();

    public void validate(@Optional Object entity){
        Map<Object, ValidationException> results = new Map<>();
        validate(entity, results);
        removeSuccessfulValidationResults(results);
        if(!results.isEmpty()) throw new ValidationsException(results);
    }

    private void validate(@Optional Object entity, @Mandatory Map<Object, ValidationException> results){
        if(entity != null){
            if(!results.containsKey(entity)){
                try {
                    entityValidator.validate(entity);
                    results.set(entity, null);
                } catch (ValidationException e){
                    results.set(entity, e);
                }

                EntityClass entityClass = entityClassProvider.get(entity.getClass());

                for(EntityField entityField : entityClass.getFields()){
                    Object value = entityField.get(entity);
                    if(value != null){
                        if(value.getClass().isAnnotationPresent(Entity.class)){
                            validate(value, results);
                        }
                    }
                }
            }
        }
    }

    private void removeSuccessfulValidationResults(@Mandatory Map<Object, ValidationException> results){
        for(Object validatedEntity : results.keys()){
            if(results.get(validatedEntity) == null){
                results.remove(validatedEntity);
            }
        }
    }
}
