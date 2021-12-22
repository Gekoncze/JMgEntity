package cz.mg.entity.explorer.gui.ui.controls;

import java.awt.*;


public interface UiComponent {
    default boolean childHasFocus(){
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        while(component != null){
            if(component == this){
                return true;
            }
            component = component.getParent();
        }
        return false;
    }
}
