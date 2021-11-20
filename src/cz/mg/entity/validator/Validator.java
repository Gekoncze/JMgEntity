package cz.mg.entity.validator;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;


public @Service interface Validator<T> {
    void validate(@Optional T object);
}
