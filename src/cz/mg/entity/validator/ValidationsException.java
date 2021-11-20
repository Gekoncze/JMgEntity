package cz.mg.entity.validator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.map.Map;


public class ValidationsException extends RuntimeException {
    private final @Mandatory @Part Map<Object, ValidationException> exceptions;

    public ValidationsException(@Mandatory Map<Object, ValidationException> exceptions) {
        super("Entity graph validation failed.");
        this.exceptions = exceptions;
    }

    public @Mandatory Map<Object, ValidationException> getExceptions() {
        return exceptions;
    }
}
