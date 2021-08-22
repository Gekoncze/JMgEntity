package cz.mg.entity;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;


public class EntityClasses {
    private static @Optional @Part EntityClassRepository repository;

    public static @Mandatory EntityClassRepository getRepository() {
        if(repository == null){
            throw new RuntimeException("Missing entity class repository.");
        }
        return repository;
    }

    public static void setRepository(EntityClassRepository repository) {
        EntityClasses.repository = repository;
    }

    private EntityClasses() {
    }
}
