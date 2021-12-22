package cz.mg.entity.explorer.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;


public @Utility class TransactionManager {
    private final @Mandatory @Part History history = new History();
    private @Optional @Link Transaction transaction;

    public TransactionManager() {
    }

    public @Mandatory History getHistory() {
        return history;
    }

    public boolean isTransactionInProgress(){
        return transaction != null;
    }

    public void transaction(@Mandatory Runnable runnable){
        beginTransaction();
        try {
            runnable.run();
            endTransaction();
        } catch (Exception e){
            rollbackTransaction();
            throw e;
        }
    }

    public void run(@Mandatory Action action){
        if(transaction != null){
            transaction.run(action);
        } else {
            throw new IllegalStateException("Transaction not started.");
        }
    }

    private void beginTransaction(){
        if(transaction == null){
            transaction = new Transaction();
        } else {
            throw new IllegalStateException("Transaction already started.");
        }
    }

    private void endTransaction(){
        if(transaction != null){
            if(transaction.count() > 0){
                history.addTransaction(transaction);
            }
            transaction = null;
        } else {
            throw new IllegalStateException("Transaction not started.");
        }
    }

    private void rollbackTransaction(){
        if(transaction != null){
            transaction.undo();
            transaction = null;
        }
    }
}
