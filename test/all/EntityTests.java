package all;

import cz.mg.classdetector.Package;
import cz.mg.collections.list.List;
import cz.mg.entity.*;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class EntityTests extends Package {
    public EntityTests() {
        super(EntityTests.class.getSimpleName(), null);
        getClasses().addCollectionLast(new List<>(
            EntityClassFactoryTest.class,
            EntityClassTest.class,
            EntityFieldFactoryTest.class,
            EntityFieldTest.class,
            EntityClassProviderTest.class,
            EntityClassInitializerTest.class
        ));
    }

    public static void main(String[] args) {
        new SingleTestPackageRunner().run(
            new EntityTests()
        );
    }
}
