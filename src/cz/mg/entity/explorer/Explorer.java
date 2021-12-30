package cz.mg.entity.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.explorer.history.TransactionManager;

import java.nio.file.Path;


public @Utility class Explorer {
    private final @Mandatory @Part TransactionManager transactionManager = new TransactionManager();
    private final @Mandatory EntityClass entityClass;
    private final @Mandatory Mapper mapper;
    private @Optional @Part Object project;
    private @Optional @Part Path path;

    public Explorer(@Mandatory EntityClass entityClass, @Mandatory Mapper mapper) {
        this.entityClass = entityClass;
        this.mapper = mapper;
    }

    public @Mandatory TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public @Mandatory EntityClass getEntityClass() {
        return entityClass;
    }

    public @Mandatory Mapper getMapper() {
        return mapper;
    }

    public @Optional Object getProject() {
        return project;
    }

    public void setProject(@Optional Object project) {
        this.project = project;
    }

    public @Optional Path getPath() {
        return path;
    }

    public void setPath(@Optional Path path) {
        this.path = path;
    }
}
