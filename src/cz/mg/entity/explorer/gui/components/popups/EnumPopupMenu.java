package cz.mg.entity.explorer.gui.components.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.ui.controls.UiPopupMenu;
import cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import static cz.mg.entity.explorer.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class EnumPopupMenu<T extends Enum> extends UiPopupMenu {
    public EnumPopupMenu(
        @Mandatory ExplorerWindow window,
        @Mandatory Class<T> clazz,
        @Mandatory SelectEventHandler<T> handler
    ) {
        super(createItems(window, clazz, handler));
    }

    private static <T extends Enum> @Mandatory List<UiValueMenuItem> createItems(
        @Mandatory ExplorerWindow window,
        @Mandatory Class<T> clazz,
        @Mandatory SelectEventHandler handler
    ){
        List<UiValueMenuItem> items = new List<>();
        for(Enum value : clazz.getEnumConstants()){
            items.addLast(new UiValueMenuItem(window, value, value.name(), handler));
        }
        return items;
    }
}
