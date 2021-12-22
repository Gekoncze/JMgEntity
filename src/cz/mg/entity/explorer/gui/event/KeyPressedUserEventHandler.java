package cz.mg.entity.explorer.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public @Utility class KeyPressedUserEventHandler extends EventHandler implements KeyListener {
    private final @Mandatory @Part Handler handler;

    public KeyPressedUserEventHandler(@Mandatory ExplorerWindow window, @Mandatory Handler handler) {
        super(window.getExplorer().getTransactionManager());
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        run(() -> handler.run(event));
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
