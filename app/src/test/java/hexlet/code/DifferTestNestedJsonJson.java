package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTestNestedJsonJson {
    private String file1;
    private String file2;
    private String expected;

    @BeforeEach
    public void init() {
        Differ.setMapper(new ObjectMapper());
        file1 = "src/test/resources/nestedJsonFile1.json";
        file2 = "src/test/resources/nestedJsonFile2.json";
        expected = """
                [
                {"op":"replace","path":"chars2","value":false},
                {"op":"replace","path":"checked","value":true},
                {"op":"replace","path":"default","value":["value1","value2"]},
                {"op":"replace","path":"id","value":null},
                {"op":"remove","path":"key1","value":"value1"},
                {"op":"add","path":"key2","value":"value2"},
                {"op":"replace","path":"numbers2","value":[22,33,44,55]},
                {"op":"remove","path":"numbers3","value":[3,4,5]},
                {"op":"add","path":"numbers4","value":[4,5,6]},
                {"op":"add","path":"obj1","value":{"nestedKey":"value","isNested":true}},
                {"op":"replace","path":"setting1","value":"Another value"},
                {"op":"replace","path":"setting2","value":300},
                {"op":"replace","path":"setting3","value":"none"}
                ]""";
    }

    @Test
    public void testParseGoodNestedJson() {
        String actual = Differ.generate(file1, file2, "json");
        assertEquals(expected, actual);
    }
}
