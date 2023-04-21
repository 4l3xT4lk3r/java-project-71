package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTestNestedJsonPlain {
    private String file1;
    private String file2;
    private String expected;

    @BeforeEach
    public void init() {
        Differ.setMapper(new ObjectMapper());
        file1 = "src/test/resources/nestedJsonFile1.json";
        file2 = "src/test/resources/nestedJsonFile2.json";
        expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
    }

    @Test
    public void testParseGoodNestedJson() {
        String actual = Differ.generate(file1, file2, "plain");
        assertEquals(expected, actual);
    }
}
