package cz.mg.entity.explorer.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.Gallery;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public @Service class GalleryIconService {
    private final @Mandatory @Shared ImageReader reader = new ImageReader();

    public void initialize(@Mandatory Gallery gallery, @Mandatory Class icons){
        try {
            for(Field field : icons.getDeclaredFields()){
                if(Modifier.isStatic(field.getModifiers())){
                    if(field.getType().equals(String.class)){
                        String name = (String) field.get(null);
                        try {
                            gallery.setImage(
                                name,
                                reader.read(icons.getResourceAsStream(name))
                            );
                        } catch (Exception e){
                            throw new RuntimeException("Could not load icon '" + name + "'.", e);
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
