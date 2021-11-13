package all;

import cz.mg.classdetector.Package;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassFactoryTest;
import cz.mg.entity.EntityClassTest;
import cz.mg.entity.EntityFieldFactoryTest;
import cz.mg.entity.EntityFieldTest;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class EntityTests extends Package {
    public EntityTests() {
        super(EntityTests.class.getSimpleName(), null);
        getClasses().addCollectionLast(new List<>(
            EntityClassFactoryTest.class,
            EntityClassTest.class,
            EntityFieldFactoryTest.class,
            EntityFieldTest.class
        ));
    }

    public static void main(String[] args) {
        new SingleTestPackageRunner().run(
            new EntityTests()
        );
    }
}
