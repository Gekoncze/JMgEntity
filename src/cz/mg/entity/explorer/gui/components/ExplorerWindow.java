package cz.mg.entity.explorer.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.gui.Gallery;
import cz.mg.entity.explorer.gui.icons.Icons;
import cz.mg.entity.explorer.gui.services.GalleryIconService;
import cz.mg.entity.explorer.gui.event.KeyDispatcherUserEventHandler;
import cz.mg.entity.explorer.gui.event.WindowCloseUserEventHandler;
import cz.mg.entity.explorer.gui.ui.controls.UiHorizontalSplitPane;
import cz.mg.entity.explorer.gui.components.other.Refreshable;
import cz.mg.entity.explorer.gui.components.views.ObjectView;
import cz.mg.entity.explorer.gui.services.NavigationCreator;
import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.explorer.gui.utilities.Navigation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;


public @Utility class ExplorerWindow extends JFrame implements Refreshable {
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 900;
    private static final int DEFAULT_DIVIDER_POSITION = 360;
    private static final int BORDER = 4;

    private final @Mandatory @Shared GalleryIconService galleryIconService = new GalleryIconService();

    private final @Mandatory @Shared ExplorerMenu menu;
    private final @Mandatory @Shared ExplorerNavigationTree navigationTree;
    private final @Mandatory @Shared ExplorerTabView tabView;

    private final @Mandatory @Part Explorer explorer;
    private final @Mandatory @Part Gallery gallery;
    private @Optional @Cache Navigation navigation;

    public ExplorerWindow(@Mandatory Mapper mapper, @Mandatory FileFilter projectFileFilter) {
        this.explorer = new Explorer(mapper);
        gallery = new Gallery();
        galleryIconService.initialize(gallery, Icons.class);
        menu = new ExplorerMenu(this, projectFileFilter);
        navigationTree = new ExplorerNavigationTree(this);
        tabView = new ExplorerTabView(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowCloseUserEventHandler(this, this::onWindowCloseButtonClicked));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(menu);
        UiHorizontalSplitPane splitPane = new UiHorizontalSplitPane(navigationTree, tabView);
        splitPane.setDividerLocation(DEFAULT_DIVIDER_POSITION);
        splitPane.setBorder(new EmptyBorder(new Insets(BORDER, BORDER, BORDER, BORDER)));
        setContentPane(splitPane);
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<>());
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<>());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyDispatcherUserEventHandler(this, this::onKeyEvent)
        );
        refresh();
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    public @Mandatory Gallery getGallery() {
        return gallery;
    }

    public @Mandatory Navigation getNavigation() {
        if(navigation == null){
            navigation = new NavigationCreator().create(
                explorer.getProject()
            );
        }

        return navigation;
    }

    public @Mandatory ExplorerMenu getMenu() {
        return menu;
    }

    public @Mandatory ExplorerNavigationTree getNavigationTree() {
        return navigationTree;
    }

    public @Mandatory ExplorerTabView getTabView() {
        return tabView;
    }

    @Override
    public void refresh() {
        navigation = null;
        navigationTree.refresh();
        tabView.refresh();
    }

    private boolean onKeyEvent(@Mandatory KeyEvent e){
        ObjectView objectView = tabView.getSelectedTab();
        if(objectView != null){
            objectView.onKeyEvent(e);
        }
        return false;
    }

    private void onWindowCloseButtonClicked(){
        menu.getActions().exit();
    }
}
