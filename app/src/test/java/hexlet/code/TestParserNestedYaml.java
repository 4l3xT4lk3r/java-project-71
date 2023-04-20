package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParserNestedYaml {
    private File file1;
    private File file2;
    private String expected;
    private Formatter formatter;

    @BeforeEach
    public void init() {
        formatter = new StylishFormatter();
        file1 = new File("src/test/resources/plainYamlFile1.yml");
        file2 = new File("src/test/resources/plainYamlFile2.yml");
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
        file1 = new File("src/test/resources/nestedYamlFile1.yml");
        file2 = new File("src/test/resources/nestedYamlFile2.yml");
        Differ.setMapper(new YAMLMapper());
        String actual = Differ.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }
}
