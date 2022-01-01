package all;

import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassPackager;
import cz.mg.classdetector.PackageBrowser;
import cz.mg.test.cli.runners.special.AllTestRunner;
import utilities.TestInitializer;


public class AllTests {
    public static void main(String[] args) {
        new TestInitializer().initialize();
        new AllTestRunner().run(
            new PackageBrowser().open(
                "cz.mg.entity",
                new ClassPackager().pack(
                    new ClassDetector().find(Configuration.TEST_JAR_PATH)
                )
            )
        );
    }
}
