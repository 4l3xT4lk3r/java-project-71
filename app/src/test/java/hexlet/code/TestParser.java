package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class TestParser {
    private File file1;
    private File file2;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        file1 = new File("src/test/resources/plainJsonFile1.json");
        file2 = new File("src/test/resources/plainJsonFile2.json");
        Parser.setMapper(mapper);
    }

    @Test
    public void testParsePlainGoodJson() {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Parser.parse(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseNestedGoodJson() {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        file1 = new File("src/test/resources/nestedJsonFile1.json");
        file2 = new File("src/test/resources/nestedJsonFile2.json");
        String actual = Parser.parse(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseFileNotExist() {
        file2 = new File("FileNotExist.json");
        String actual;
        actual = Parser.parse(file1, file2);
        assertNull(actual);
    }

    @Test
    public void testParseWrongJson() {
        file1 = new File("src/test/resources/plainWrongJsonFile.json");
        String actual;
        actual = Parser.parse(file1, file2);
        assertNull(actual);
    }

    @Test
    public void testParseGoodYaml() {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        file1 = new File("src/test/resources/plainYamlFile1.yml");
        file2 = new File("src/test/resources/plainYamlfile2.yml");
        Parser.setMapper(new YAMLMapper());
        String actual = Parser.parse(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseWrongYml() {
        file1 = new File("src/test/resources/plainYamlWrongFile.yml");
        file2 = new File("src/test/resources/plainYamlfile2.yml");
        Parser.setMapper(new YAMLMapper());
        String actual;
        actual = Parser.parse(file1, file2);
        assertNull(actual);
    }
}
