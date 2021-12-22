package cz.mg.entity.explorer.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import java.awt.*;
import java.awt.event.KeyEvent;


public @Utility class KeyDispatcherUserEventHandler extends EventHandler implements KeyEventDispatcher {
    private final @Mandatory @Part Handler handler;

    public KeyDispatcherUserEventHandler(@Mandatory ExplorerWindow window, @Mandatory Handler handler) {
        super(window.getExplorer().getTransactionManager());
        this.handler = handler;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        run(() -> handler.run(event));
        return false;
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
