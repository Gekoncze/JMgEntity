package manual;

import cz.mg.entity.explorer.gui.components.ExplorerWindow;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestInitializer;
import utilities.TestMapper;
import utilities.TestRoot;

import javax.swing.filechooser.FileNameExtensionFilter;


public class ExplorerApplicationTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(ExplorerApplicationTest.class);
    }

    @TestCase
    public void test(){
        new TestInitializer().initialize();
        ExplorerWindow window = new ExplorerWindow(
            TestRoot.entity,
            new TestMapper(),
            new FileNameExtensionFilter("Test files (*.test)", "test")
        );
        window.setVisible(true);
    }
}
