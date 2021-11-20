package cz.mg.entity.validator;

import cz.mg.annotations.requirement.Mandatory;


public class ValidationException extends RuntimeException {
    public ValidationException(@Mandatory String message) {
        super(message);
    }
}
