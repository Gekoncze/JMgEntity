package utilities;

import cz.mg.entity.mapper.Mapper;
import cz.mg.entity.mapper.ObjectMapperRepositoryBuilder;


public class TestMapper extends Mapper<TestRoot> {
    public TestMapper() {
        super(
            new ObjectMapperRepositoryBuilder()
                .addEntity(TestRoot.class)
                .build()
        );
    }
}
