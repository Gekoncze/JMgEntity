package cz.mg.entity.explorer.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.explorer.gui.ui.dialogs.UiErrorMessageDialog;
import cz.mg.entity.explorer.history.TransactionManager;


public abstract @Utility class EventHandler {
    private final @Mandatory @Link TransactionManager transactionManager;

    public EventHandler(@Mandatory TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void run(@Mandatory Runnable runnable){
        try {
            if(transactionManager.isTransactionInProgress()){
                runnable.run();
            } else {
                transactionManager.transaction(runnable);
            }
        } catch (RuntimeException e){
            new UiErrorMessageDialog(e).show();
        }
    }
}
