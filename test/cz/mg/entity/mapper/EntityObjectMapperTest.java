package cz.mg.entity.mapper;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassFactory;
import cz.mg.entity.mapper.common.EntityObjectMapper;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestRoot;


public class EntityObjectMapperTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityObjectMapperTest.class);
    }

    @TestCase
    public void test(){
        TestRoot root = new TestRoot();
        root.name = "test root";
        root.branches = new List<>();

        ObjectMapper objectMapper = new EntityObjectMapper(
            new EntityClassFactory().create(TestRoot.class)
        );

        assertNotNull(objectMapper.create(null));
        assertEquals(TestRoot.class, objectMapper.create(null).getClass());
        assertEquals(TestRoot.class.getSimpleName(), objectMapper.getName());
        assertEquals(null, objectMapper.getValue(root));

        List<Object> fields = objectMapper.getFields(root);
        assertNotNull(fields);
        assertEquals(2, fields.count());
        assertSame(root.branches, fields.get(0));
        assertSame(root.name, fields.get(1));
    }
}
