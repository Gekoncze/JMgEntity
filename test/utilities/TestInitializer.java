package utilities;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassInitializer;


public class TestInitializer {
    public TestInitializer() {
    }

    public void initialize(){
        new EntityClassInitializer().init(new List<>(
            TestRoot.class,
            TestBranch.class,
            TestLeaf.class
        ));
    }
}
