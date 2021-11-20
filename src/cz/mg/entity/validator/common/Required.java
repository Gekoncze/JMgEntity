package cz.mg.entity.validator.common;

import cz.mg.entity.validator.Validator;
import cz.mg.entity.validator.ValidationException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Required {
    Validator validator = (object) -> {
        if(object == null){
            throw new ValidationException("Missing required value.");
        }
    };
}
