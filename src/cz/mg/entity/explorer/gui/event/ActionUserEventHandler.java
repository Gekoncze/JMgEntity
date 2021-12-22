package cz.mg.entity.explorer.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.explorer.gui.components.ExplorerWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public @Utility class ActionUserEventHandler extends EventHandler implements ActionListener {
    private final @Mandatory @Part Handler handler;

    public ActionUserEventHandler(@Mandatory ExplorerWindow window, @Mandatory Handler handler) {
        super(window.getExplorer().getTransactionManager());
        this.handler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        run(handler::run);
    }

    public interface Handler {
        void run();
    }
}
