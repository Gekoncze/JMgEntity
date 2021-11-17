package all;

import cz.mg.classdetector.ClassDetector;
import cz.mg.classdetector.ClassPackager;
import cz.mg.classdetector.PackageBrowser;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class AllMapperTests {
    public static void main(String[] args) {
        new SingleTestPackageRunner().run(
            new PackageBrowser().open(
                "cz.mg.entity.mapper",
                new ClassPackager().pack(
                    new ClassDetector().find(Configuration.TEST_JAR_PATH)
                )
            )
        );
    }
}
