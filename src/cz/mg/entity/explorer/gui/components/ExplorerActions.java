package cz.mg.entity.explorer.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.entity.explorer.gui.ui.dialogs.UiOpenDialog;
import cz.mg.entity.explorer.gui.ui.dialogs.UiSaveDialog;
import cz.mg.entity.explorer.services.ExplorerProjectService;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.nio.file.Path;


public @Utility class ExplorerActions {
    private final @Mandatory @Shared ExplorerProjectService explorerProjectService = new ExplorerProjectService();

    private final @Mandatory @Link ExplorerWindow window;
    private final @Mandatory @Part FileFilter projectFileFilter;

    public ExplorerActions(@Mandatory ExplorerWindow window, @Mandatory @Part FileFilter projectFileFilter) {
        this.window = window;
        this.projectFileFilter = projectFileFilter;
    }

    // FILE

    public boolean newProject(){
        if(window.getExplorer().getProject() != null){
            if(!closeProject()){
                return false;
            }
        }

        String name = JOptionPane.showInputDialog(this, "New Project");
        if(name != null){
            explorerProjectService.newProject(
                window.getExplorer(),
                name
            );
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean openProject(){
        Path selectedPath = new UiOpenDialog("Open project", projectFileFilter).show();

        if(selectedPath != null){
            if(window.getExplorer().getProject() != null){
                if(!closeProject()){
                    return false;
                }
            }

            explorerProjectService.openProject(
                window.getExplorer(),
                selectedPath.toAbsolutePath()
            );
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean saveProject(){
        if(window.getExplorer().getProject() == null){
            return false;
        }

        if(window.getExplorer().getPath() != null){
            explorerProjectService.saveProject(
                window.getExplorer()
            );
            return true;
        } else {
            return saveProjectAs();
        }
    }

    public boolean saveProjectAs(){
        if(window.getExplorer().getProject() == null){
            return false;
        }

        Path selectedPath = new UiSaveDialog("Save project", projectFileFilter).show();

        if(selectedPath != null){
            explorerProjectService.saveProjectAs(
                window.getExplorer(),
                selectedPath.toAbsolutePath()
            );
            return true;
        } else {
            return false;
        }
    }

    public boolean closeProject(){
        if(window.getExplorer().getProject() == null){
            return true;
        }

        UiConfirmDialog.Choice choice = new UiConfirmDialog(
            "Close project",
            "Would you like to save project before closing?"
        ).show();

        if(choice == UiConfirmDialog.Choice.CANCEL){
            return false;
        }

        if(choice == UiConfirmDialog.Choice.YES){
            if(!saveProject()){
                return false;
            }
        }

        window.getTabView().closeAllTabs();

        explorerProjectService.closeProject(
            window.getExplorer()
        );
        refresh();
        return true;
    }

    public boolean exit(){
        if(!closeProject()){
            return false;
        }

        window.dispose();
        return true;
    }

    // EDIT

    public void undo(){
        window.getExplorer().getTransactionManager().getHistory().undo();
        window.refresh();
    }

    public void redo(){
        window.getExplorer().getTransactionManager().getHistory().redo();
        window.refresh();
    }

    // VIEW

    public void refresh(){
        window.refresh();
    }

    public void closeActiveTab(){
        window.getTabView().closeActiveTab();
    }

    public void selectNextTab(){
        window.getTabView().selectNextTab();
    }

    public void selectPreviousTab(){
        window.getTabView().selectPreviousTab();
    }
}
