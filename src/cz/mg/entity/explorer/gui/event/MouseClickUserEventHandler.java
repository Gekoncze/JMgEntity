package cz.mg.entity.explorer.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public @Utility class MouseClickUserEventHandler extends EventHandler implements MouseListener {
    private final @Mandatory @Part Handler handler;

    public MouseClickUserEventHandler(@Mandatory ExplorerWindow window, @Mandatory Handler handler) {
        super(window.getExplorer().getTransactionManager());
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        run(() -> handler.run(event));
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    public interface Handler {
        void run(MouseEvent e);
    }
}
