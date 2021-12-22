package cz.mg.entity.explorer.gui.ui.controls.field.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.controls.UiImage;
import cz.mg.entity.explorer.gui.ui.controls.UiPanel;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiFieldBase;

import java.awt.*;


public @Utility class UiFieldBaseWrapper extends UiPanel {
    private static final int PADDING = 2;

    private final @Mandatory @Shared UiFieldImageProvider fieldImageProvider;
    private final @Mandatory @Shared UiImage imageComponent;
    private final @Mandatory @Shared UiFieldBase fieldBase;

    public UiFieldBaseWrapper(@Mandatory UiFieldImageProvider fieldImageProvider, @Mandatory UiFieldBase fieldBase) {
        super(0, PADDING, UiAlignment.LEFT);
        this.fieldImageProvider = fieldImageProvider;
        this.imageComponent = new UiImage();
        this.fieldBase = fieldBase;

        FontMetrics fontMetrics = fieldBase.getFontMetrics(fieldBase.getFont());
        imageComponent.setPreferredSize(new Dimension(fontMetrics.getHeight(), fontMetrics.getHeight()));
        addHorizontal(imageComponent, 0, 0, UiAlignment.LEFT, UiFill.BOTH);
        addHorizontal(fieldBase, 1, 0, UiAlignment.LEFT, UiFill.BOTH);
        rebuild();
    }

    public @Mandatory UiFieldBase getFieldBase() {
        return fieldBase;
    }

    public void lock(){
        fieldBase.lock();
    }

    public void unlock(){
        fieldBase.unlock();
    }

    public boolean isLocked() {
        return fieldBase.isLocked();
    }

    public @Optional Object getValue(){
        return fieldBase.getValue();
    }

    public void setValue(@Optional Object value){
        fieldBase.setValue(value);
        imageComponent.setImage(fieldImageProvider.get(value));
    }
}
