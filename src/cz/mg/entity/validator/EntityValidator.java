package cz.mg.entity.validator;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public @Service class EntityValidator {
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    public void validate(@Optional Object entity){
        if(entity != null){
            if(entity.getClass().isAnnotationPresent(Entity.class)){
                validateClass(entity, entityClassProvider.get(entity.getClass()));
            } else {
                throw new ValidationException("Missing entity annotation for object of type '" + entity.getClass().getSimpleName() + "'.");
            }
        }
    }

    private void validateClass(@Mandatory Object entity, @Mandatory EntityClass entityClass){
        for(EntityField entityField : entityClass.getFields()){
            validateField(entity, entityField);
        }
    }

    private void validateField(@Mandatory Object entity, @Mandatory EntityField entityField){
        Object value = entityField.get(entity);
        if(value != null) validateType(entityField.getType(), value.getClass());
        validateValue(value, entityField);
    }

    private void validateType(@Mandatory Class expectation, @Mandatory Class reality){
        if(!expectation.isAssignableFrom(reality)){
            throw new ValidationException("Expected object of type '" + expectation.getSimpleName() + "', but got '" + reality.getSimpleName() + "'.");
        }
    }

    private void validateValue(@Optional Object value, @Mandatory EntityField entityField){
        for(Annotation annotation : entityField.getField().getAnnotations()){
            for(Field annotationField : annotation.annotationType().getDeclaredFields()){
                if(Validator.class.isAssignableFrom(annotationField.getType())){
                    try {
                        Validator validator = (Validator) annotationField.get(null);
                        validator.validate(value);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
