package cz.mg.entity.explorer.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.storage.EntityReader;
import cz.mg.entity.storage.EntityWriter;

import java.nio.file.Path;


public @Service class ExplorerProjectService {
    private final @Mandatory @Shared EntityWriter entityWriter = new EntityWriter();
    private final @Mandatory @Shared EntityReader entityReader = new EntityReader();

    public void newProject(@Mandatory Explorer explorer, @Mandatory Object project) {
        explorer.getTransactionManager().getHistory().clear();
        explorer.setProject(project);
        explorer.setPath(null);
    }

    public void openProject(@Mandatory Explorer explorer, @Mandatory Path path) {
        explorer.getTransactionManager().getHistory().clear();
        explorer.setProject(entityReader.read(path.toString(), explorer.getMapper()));
        explorer.setPath(path);
    }

    public void saveProject(@Mandatory Explorer explorer) {
        if(explorer.getPath() != null){
            entityWriter.write(
                explorer.getPath().toString(),
                explorer.getProject(),
                explorer.getMapper()
            );
        } else {
            throw new RuntimeException("Missing path to project file.");
        }
    }

    public void saveProjectAs(@Mandatory Explorer explorer, @Mandatory Path path) {
        explorer.setPath(path);
        saveProject(explorer);
    }

    public void closeProject(@Mandatory Explorer explorer) {
        explorer.getTransactionManager().getHistory().clear();
        explorer.setProject(null);
        explorer.setPath(null);
    }
}
