package all;

import cz.mg.test.cli.runners.BulkTestPackageRunner;


public class AllTests {
    public static void main(String[] args) {
        new BulkTestPackageRunner().run(
            new EntityTests(),
            new MapperTests()
        );
    }
}
