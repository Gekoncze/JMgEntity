package cz.mg.entity.explorer.gui.ui.dialogs;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Path;


public @Utility class UiSaveDialog {
    private final @Mandatory @Shared JFileChooser fileChooser = new JFileChooser();

    public UiSaveDialog(@Mandatory String title, @Mandatory FileFilter fileFilter) {
        fileChooser.setDialogTitle(title);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setFileFilter(fileFilter);
    }

    public @Optional Path show(){
        fileChooser.showSaveDialog(null);
        return toPath(fileChooser.getSelectedFile());
    }

    private static @Optional Path toPath(@Optional File file){
        if(file != null){
            return file.toPath();
        } else {
            return null;
        }
    }
}
