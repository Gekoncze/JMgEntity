package cz.mg.entity.explorer.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.explorer.gui.event.MouseClickUserEventHandler;
import cz.mg.entity.explorer.gui.icons.Icons;
import cz.mg.entity.explorer.gui.services.ObjectNameProvider;
import cz.mg.entity.explorer.gui.ui.controls.UiButton;
import cz.mg.entity.explorer.gui.ui.controls.UiText;
import cz.mg.entity.explorer.gui.ui.controls.field.UiValueField;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.entity.explorer.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.services.DeleteService;
import cz.mg.entity.explorer.services.OwnershipService;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.popups.EntityClassPopupMenu;

import java.awt.event.MouseEvent;


public @Utility class PartField extends ObjectField {
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton createButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton deleteButton;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    public PartField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(window, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(window, this::getIcon, UiObjectFieldBase::new);
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(window, this::onMouseClicked));
        this.createButton = new UiButton(window, getIcon(Icons.CREATE), null, "Create", this::onCreateButtonClicked);
        this.openButton = new UiButton(window, getIcon(Icons.OPEN), null, "Open", this::onOpenButtonClicked);
        this.deleteButton = new UiButton(window, getIcon(Icons.DELETE), null, "Delete", this::onDeleteButtonClicked);
        this.popupMenu = new EntityClassPopupMenu(window, entityClassProvider.get(type), this::onCreate);
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.createButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.openButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.deleteButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
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
            popupMenu.select(field.getBase());
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

    private void onCreate(@Mandatory EntityClass entityClass){
        setValue(entityClass.newInstance());
        getWindow().refresh();
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();
    }
}
