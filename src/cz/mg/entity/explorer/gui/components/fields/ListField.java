package cz.mg.entity.explorer.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.event.MouseClickUserEventHandler;
import cz.mg.entity.explorer.gui.icons.Icons;
import cz.mg.entity.explorer.gui.services.ObjectNameProvider;
import cz.mg.entity.explorer.gui.ui.controls.UiButton;
import cz.mg.entity.explorer.gui.ui.controls.UiList;
import cz.mg.entity.explorer.gui.ui.controls.UiText;
import cz.mg.entity.explorer.gui.ui.controls.field.UiValueField;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.entity.explorer.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.services.DeleteService;
import cz.mg.entity.explorer.services.OwnershipService;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.services.ListFieldFactory;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.lang.annotation.Annotation;


public @Utility class ListField extends ObjectField {
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();
    private final @Mandatory @Shared ListFieldFactory fieldFactory = new ListFieldFactory();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton createButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton deleteButton;

    private final @Mandatory @Shared UiList list;
    private final @Mandatory @Link Class parameterType;
    private final @Mandatory @Link Class<? extends Annotation> ownership;

    public ListField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label,
        @Mandatory Class parameterType,
        @Mandatory Class ownership
    ) {
        super(window, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(window, this::getIcon, UiObjectFieldBase::new);
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(window, this::onMouseClicked));
        this.createButton = new UiButton(window, getIcon(Icons.CREATE), null, "Create", this::onCreateButtonClicked);
        this.openButton = new UiButton(window, getIcon(Icons.OPEN), null, "Open", this::onOpenButtonClicked);
        this.deleteButton = new UiButton(window, getIcon(Icons.DELETE), null, "Delete", this::onDeleteButtonClicked);
        this.list = new UiList();
        this.parameterType = parameterType;
        this.ownership = ownership;
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.createButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.openButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.deleteButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        add(this.list, 0, 1, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH, getComponentCount(), 1);
        rebuild();
        refresh();
    }

    private void onMouseClicked(@Mandatory MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onOpenButtonClicked();
            }
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            setValue(new List<>());
            getWindow().refresh();
        }
    }

    private void onOpenButtonClicked() {
        Object value = getValue();
        if(value != null){
            getWindow().getTabView().open(value);
        }
    }

    private void onDeleteButtonClicked() {
        Object value = getValue();
        if(value != null){
            setValue(null);
            if(!ownershipService.hasOwner(getWindow().getExplorer(), value)){
                UiConfirmDialog.Choice choice = new UiConfirmDialog(
                    "Delete object?",
                    "Would you like to delete the object '" + objectNameProvider.get(value) + "'?"
                ).show();

                if(choice == UiConfirmDialog.Choice.YES){
                    deleteService.delete(getWindow().getExplorer(), value);
                    getWindow().refresh();
                }
            }
        }
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();

        this.list.clear();
        List list = (List) getValue();
        if(list != null){
            for(int i = 0; i < list.count(); i++){
                ObjectField field = fieldFactory.create(
                    getWindow(), list, i, parameterType, ownership
                );
                field.setBorder(null);
                field.setBackground(null);
                ((JComponent)field.getComponent(1)).setBorder(null);
                ((JComponent)field.getComponent(1)).setBackground(null);
                this.list.addItem(field);
            }
        }
        this.list.rebuild();
    }
}
