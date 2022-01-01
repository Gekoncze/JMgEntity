package cz.mg.entity.mapper;

import cz.mg.entity.mapper.common.EnumObjectMapper;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;
import utilities.TestEnum;


public class EnumObjectMapperTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(EnumObjectMapperTest.class);
    }

    @TestCase
    public void test(){
        TestEnum value = TestEnum.TUESDAY;
        ObjectMapper objectMapper = new EnumObjectMapper(TestEnum.class);
        assertEquals(value, objectMapper.create("TUESDAY"));
        assertEquals(TestEnum.class.getSimpleName(), objectMapper.getName());
        assertEquals("TUESDAY", objectMapper.getValue(value));
        assertEquals(0, objectMapper.getFields(value).count());
    }
}
