package all;

import cz.mg.classdetector.Package;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.EntityObjectMapperTest;
import cz.mg.entity.mapper.EnumObjectMapperTest;
import cz.mg.test.cli.runners.SingleTestPackageRunner;


public class MapperTests extends Package {
    public MapperTests() {
        super(MapperTests.class.getSimpleName(), null);
        getClasses().addCollectionLast(new List<>(
            EntityObjectMapperTest.class,
            EnumObjectMapperTest.class
        ));
    }

    public static void main(String[] args) {
        new SingleTestPackageRunner().run(new MapperTests());
    }
}
