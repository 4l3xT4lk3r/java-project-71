package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTestNestedYaml {
    private String file1;
    private String file2;
    private String expected;

    @BeforeEach
    public void init() {
        Differ.setMapper(new YAMLMapper());
        file1 = "src/test/resources/nestedYamlFile1.yml";
        file2 = "src/test/resources/nestedYamlFile2.yml";
        expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
    }
    @Test
    public void testParseGoodNestedYaml() {
        String actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expected, actual);
    }
}
