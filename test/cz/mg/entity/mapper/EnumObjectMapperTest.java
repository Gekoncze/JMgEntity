package cz.mg.entity.mapper;

import cz.mg.entity.mapper.common.EnumObjectMapper;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class EnumObjectMapperTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EnumObjectMapperTest.class);
    }

    @TestCase
    public void test(){
        TestEnum value = TestEnum.TWO;
        ObjectMapper objectMapper = new EnumObjectMapper(TestEnum.class);
        assertEquals(value, objectMapper.create("TWO"));
        assertEquals(TestEnum.class.getSimpleName(), objectMapper.getName());
        assertEquals("TWO", objectMapper.getValue(value));
    }
}
