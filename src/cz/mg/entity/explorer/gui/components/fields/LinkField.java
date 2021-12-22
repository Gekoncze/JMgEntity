package cz.mg.entity.explorer.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.event.FocusGainedUserEventHandler;
import cz.mg.entity.explorer.gui.event.FocusLostUserEventHandler;
import cz.mg.entity.explorer.gui.event.KeyPressedUserEventHandler;
import cz.mg.entity.explorer.gui.event.MouseClickUserEventHandler;
import cz.mg.entity.explorer.gui.icons.Icons;
import cz.mg.entity.explorer.gui.ui.controls.UiButton;
import cz.mg.entity.explorer.gui.ui.controls.UiText;
import cz.mg.entity.explorer.gui.ui.controls.field.UiValueField;
import cz.mg.entity.explorer.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.enums.UiKey;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.entity.explorer.gui.components.popups.EntitySearchPopupMenu;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class LinkField extends ObjectField {
    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton searchButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton editButton;
    private final @Mandatory @Shared UiButton clearButton;
    private final @Mandatory @Shared EntitySearchPopupMenu popupMenu;

    public LinkField(
        @Mandatory ExplorerWindow window,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(window, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(window, this::getIcon, UiObjectFieldBase::new);
        this.field.getBase().addFocusListener(new FocusGainedUserEventHandler(window, this::onFocusGained));
        this.field.getBase().addFocusListener(new FocusLostUserEventHandler(window, this::onFocusLost));
        this.field.getBase().addKeyListener(new KeyPressedUserEventHandler(window, this::onKeyPressed));
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(window, this::onMouseClicked));
        this.searchButton = new UiButton(window, getIcon(Icons.SEARCH), null, "Search", this::onSearchButtonClicked);
        this.openButton = new UiButton(window, getIcon(Icons.OPEN), null, "Open", this::onOpenButtonClicked);
        this.editButton = new UiButton(window, getIcon(Icons.EDIT), null, "Edit", this::onEditButtonClicked);
        this.clearButton = new UiButton(window, getIcon(Icons.CLEAR), null, "Clear", this::onClearButtonClicked);
        this.popupMenu = new EntitySearchPopupMenu(window, this::setValue);
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.searchButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.openButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.editButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.clearButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        rebuild();
        refresh();
    }

    private void onFocusGained(){
        field.getBase().selectAll();
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onKeyPressed(@Mandatory KeyEvent event) {
        if(event.getKeyCode() == UiKey.ESCAPE){
            refresh();
        }

        if(event.getKeyCode() == UiKey.SPACE && event.isControlDown()){
            showSelectionMenu();
            event.consume();
        }
    }

    private void onMouseClicked(@Mandatory MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                field.unlock();
            }
        }
    }

    private void onSearchButtonClicked(){
        showSelectionMenu();
    }

    private void onOpenButtonClicked(){
        getWindow().getTabView().open(getValue());
    }

    private void onEditButtonClicked(){
        field.unlock();
    }

    private void onClearButtonClicked(){
        setValue(null);
    }

    private void showSelectionMenu(){
        popupMenu.search(
            field.getBase(),
            getType(),
            field.getBase().getText(),
            getWindow().getNavigation()
        );
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();
    }
}
