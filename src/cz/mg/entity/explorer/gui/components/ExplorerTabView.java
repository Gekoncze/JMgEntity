package cz.mg.entity.explorer.gui.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.gui.event.FocusGainedUserEventHandler;
import cz.mg.entity.explorer.gui.event.MouseClickUserEventHandler;
import cz.mg.entity.explorer.gui.services.ObjectIconProvider;
import cz.mg.entity.explorer.gui.ui.controls.UiButton;
import cz.mg.entity.explorer.gui.ui.controls.UiLabel;
import cz.mg.entity.explorer.gui.ui.controls.UiPanel;
import cz.mg.entity.explorer.gui.ui.controls.UiText;
import cz.mg.entity.explorer.services.SearchService;
import cz.mg.entity.explorer.gui.components.other.Refreshable;
import cz.mg.entity.explorer.gui.components.views.ObjectView;
import cz.mg.entity.explorer.gui.services.ObjectNameProvider;
import cz.mg.entity.explorer.gui.services.ObjectViewFactory;

import javax.swing.*;
import java.awt.*;

import static cz.mg.entity.explorer.gui.ui.enums.UiFill.BOTH;
import static cz.mg.entity.explorer.gui.ui.enums.UiFill.NONE;
import static cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment.MIDDLE;


public class ExplorerTabView extends JTabbedPane implements Refreshable {
    private static final int PADDING = 8;

    private final @Mandatory @Shared SearchService searchService = new SearchService();
    private final @Mandatory @Shared ObjectIconProvider objectIconProvider = new ObjectIconProvider();
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared ObjectViewFactory objectViewFactory = new ObjectViewFactory();

    private final @Mandatory @Link ExplorerWindow window;

    public ExplorerTabView(@Mandatory ExplorerWindow window) {
        this.window = window;
    }

    public void open(@Optional Object object){
        if(object != null){
            Integer tabIndex = getTabIndex(object);
            if(tabIndex == null){
                openNew(object);
            } else {
                openExisting(tabIndex);
            }
        }
    }

    private void openExisting(@Mandatory Integer tabIndex){
        setSelectedIndex(tabIndex);
    }

    private void openNew(@Mandatory Object object){
        addNewTab(object, objectViewFactory.create(window, object));
    }

    public void closeTab(int index){
        remove(index);
    }

    public void closeActiveTab(){
        int index = getSelectedIndex();
        if(index != -1){
            closeTab(index);
        }
    }

    public void closeAllTabs(){
        while(getTabCount() > 0){
            closeTab(0);
        }
    }

    public void selectNextTab(){
        if(getTabCount() == 0) return;
        int i = getSelectedIndex() + 1;
        if(i >= getTabCount()) i = 0;
        setSelectedIndex(i);
    }

    public void selectPreviousTab(){
        if(getTabCount() == 0) return;
        int i = getSelectedIndex() - 1;
        if(i < 0) i = getTabCount() - 1;
        setSelectedIndex(i);
    }

    private void selectTab(@Mandatory UiPanel header){
        for(int i = 0; i < getTabCount(); i++){
            Component component = getTabComponentAt(i);
            if(component == header){
                setSelectedIndex(i);
            }
        }
    }

    public @Optional ObjectView getSelectedTab(){
        if(getSelectedIndex() != -1){
            return getTab(getSelectedIndex());
        } else {
            return null;
        }
    }

    public @Mandatory ObjectView getTab(int i){
        return (ObjectView) getComponentAt(i);
    }

    private void addNewTab(@Mandatory Object object, @Mandatory ObjectView view){
        addTab(null, null, (Component) view);
        setTabComponentAt(getTabCount() - 1, createTabHeader(object, (Component) view));
        setSelectedIndex(getTabCount() - 1);
    }

    private @Mandatory UiPanel createTabHeader(@Mandatory Object object, @Mandatory Component component) {
        UiPanel header = new UiPanel(0, PADDING, MIDDLE);

        JComponent label = createTabHeaderLabel(object);
        label.addMouseListener(new MouseClickUserEventHandler(window, event -> selectTab(header)));
        label.addFocusListener(new FocusGainedUserEventHandler(window, () -> selectTab(header)));
        header.add(label, 0, 0, 0, 0, MIDDLE, BOTH);

        UiButton closeButton = new UiButton(window, null, "x", "Close", () -> remove(component));
        closeButton.setForeground(new Color(180, 180, 180, 255));
        header.add(closeButton, 1, 0, 0, 0, MIDDLE, NONE);

        header.rebuild();
        return header;
    }

    private @Mandatory JComponent createTabHeaderLabel(@Mandatory Object object){
        Image icon = objectIconProvider.get(window.getGallery(), object);
        String name = objectNameProvider.get(object);
        return icon != null ? new UiLabel(icon, name) : new UiText(name, UiText.FontStyle.BOLD);
    }

    private @Optional Integer getTabIndex(@Mandatory Object object){
        for(int i = 0; i < getTabCount(); i++){
            if(getTab(i).getObject() == object){
                return i;
            }
        }
        return null;
    }

    private void closeDeletedObjectTabs(){
        for(int i = 0; i < getTabCount(); i++){
            Object object = getTab(i).getObject();
            if(!exists(object)){
                closeTab(i);
                i--;
            }
        }
    }

    private boolean exists(@Mandatory Object object){
        return !searchService.findUsages(window.getExplorer(), object).isEmpty();
    }

    @Override
    public void refresh() {
        closeDeletedObjectTabs();
        for(int i = 0; i < getTabCount(); i++){
            getTab(i).refresh();
        }
    }
}
