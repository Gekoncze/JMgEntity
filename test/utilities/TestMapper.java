package utilities;

import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.mapper.ObjectMapperRepositoryBuilder;


public class TestMapper extends Mapper {
    public TestMapper() {
        super(
            new ObjectMapperRepositoryBuilder()
                .addEntity(TestRoot.class)
                .addEntity(TestBranch.class)
                .addEntity(TestLeaf.class)
                .addEntity(TestSubclass.class)
                .addEnum(TestEnum.class)
                .build()
        );
    }
}
