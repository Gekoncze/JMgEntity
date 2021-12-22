package cz.mg.entity.explorer.history;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class HistoryTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(HistoryTest.class);
    }

    @TestCase(order = 0)
    public void testEmptyUndo(){
        History history = new History();
        history.setLimit(3);

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());

        history.undo();

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());
    }

    @TestCase(order = 1)
    public void testEmptyRedo(){
        History history = new History();
        history.setLimit(3);

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());

        history.redo();

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());
    }

    @TestCase(order = 2)
    public void testSingleAction(){
        List<String> log = new List<>();
        History history = new History();
        history.setLimit(3);
        Transaction transaction;

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new Action() {
            @Override
            public void redo() {
                log.addLast("Redo");
            }

            @Override
            public void undo() {
                log.addLast("Undo");
            }
        });

        assertEquals(0, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(1, log.count());
        assertEquals("Redo", log.getLast());

        history.undo();

        assertEquals(-1, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(2, log.count());
        assertEquals("Undo", log.getLast());

        history.undo();

        assertEquals(-1, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(2, log.count());
        assertEquals("Undo", log.getLast());

        history.redo();

        assertEquals(0, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(3, log.count());
        assertEquals("Redo", log.getLast());

        history.redo();

        assertEquals(0, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(3, log.count());
        assertEquals("Redo", log.getLast());
    }

    @TestCase(order = 3)
    public void testTrimLeft(){
        List<String> log = new List<>();
        History history = new History();
        history.setLimit(3);
        Transaction transaction;

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 0));

        assertEquals(0, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(1, log.count());
        assertEquals("Redo 0", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 1));

        assertEquals(1, history.getPosition());
        assertEquals(2, history.count());
        assertEquals(2, log.count());
        assertEquals("Redo 1", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 2));

        assertEquals(2, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(3, log.count());
        assertEquals("Redo 2", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 3));

        assertEquals(2, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(4, log.count());
        assertEquals("Redo 3", log.getLast());

        history.redo();
        history.redo();
        history.redo();

        assertEquals(2, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(4, log.count());
        assertEquals("Redo 3", log.getLast());

        history.undo();
        history.undo();
        history.undo();
        history.undo();
        history.undo();

        assertEquals(-1, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(7, log.count());
        assertEquals("Undo 1", log.getLast());
    }

    @TestCase(order = 4)
    public void testTrimRight(){
        List<String> log = new List<>();
        History history = new History();
        history.setLimit(3);
        Transaction transaction;

        assertEquals(-1, history.getPosition());
        assertEquals(0, history.count());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 0));

        assertEquals(0, history.getPosition());
        assertEquals(1, history.count());
        assertEquals(1, log.count());
        assertEquals("Redo 0", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 1));

        assertEquals(1, history.getPosition());
        assertEquals(2, history.count());
        assertEquals(2, log.count());
        assertEquals("Redo 1", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 2));

        assertEquals(2, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(3, log.count());
        assertEquals("Redo 2", log.getLast());

        history.undo();

        assertEquals(1, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(4, log.count());
        assertEquals("Undo 2", log.getLast());

        history.undo();

        assertEquals(0, history.getPosition());
        assertEquals(3, history.count());
        assertEquals(5, log.count());
        assertEquals("Undo 1", log.getLast());

        history.addTransaction(transaction = new Transaction());
        transaction.run(new TestAction(log, 3));

        assertEquals(1, history.getPosition());
        assertEquals(2, history.count());
        assertEquals(6, log.count());
        assertEquals("Redo 3", log.getLast());

        history.redo();

        assertEquals(1, history.getPosition());
        assertEquals(2, history.count());
        assertEquals(6, log.count());
        assertEquals("Redo 3", log.getLast());
    }

    private static class TestAction implements Action {
        private final @Mandatory @Value List<String> log;
        private final @Value int id;

        public TestAction(@Mandatory List<String> log, int id) {
            this.log = log;
            this.id = id;
        }

        @Override
        public void redo() {
            log.addLast("Redo " + id);
        }

        @Override
        public void undo() {
            log.addLast("Undo " + id);
        }
    }
}
