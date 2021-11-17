package cz.mg.entity.mapper;

import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassFactory;
import cz.mg.entity.mapper.mappers.EntityObjectMapper;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EntityObjectMapperTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EntityObjectMapperTest.class);
    }

    @TestCase
    public void test(){
        TestTree tree = new TestTree();
        tree.height = 12;
        tree.branches = new List<>();

        ObjectMapper objectMapper = new EntityObjectMapper(
            new EntityClassFactory().create(TestTree.class)
        );

        assertNotNull(objectMapper.create(null));
        assertEquals(TestTree.class, objectMapper.create(null).getClass());
        assertEquals("TestTree", objectMapper.getName());
        assertEquals(null, objectMapper.getValue(tree));

        List<Object> fields = objectMapper.getFields(tree);
        assertNotNull(fields);
        assertEquals(2, fields.count());
        assertSame(tree.branches, fields.get(0));
        assertSame(tree.height, fields.get(1));
    }
}
