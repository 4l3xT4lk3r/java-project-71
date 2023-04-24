package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class DifferTest {
    private static String jsonFile1;
    private static String jsonFile2;
    private static String yamlFile1;
    private static String yamlFile2;
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    private static String readStringFromFile(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    @BeforeAll
    public static void init() throws IOException {
        expectedStylish = readStringFromFile("src/test/resources/expectedStylish.txt");
        expectedPlain = readStringFromFile("src/test/resources/expectedPlain.txt");
        expectedJson = readStringFromFile("src/test/resources/expectedJson.txt");
        jsonFile1 = "src/test/resources/nestedJsonFile1.json";
        jsonFile2 = "src/test/resources/nestedJsonFile2.json";
        yamlFile1 = "src/test/resources/nestedYamlFile1.yml";
        yamlFile2 = "src/test/resources/nestedYamlFile2.yml";
    }

    @Test
    public void testParseFileNotExist() {
        String actual = Differ.generate("FileNotExist.json", jsonFile2, "stylish");
        assertEquals("FileNotExist.json (No such file or directory)", actual);
    }

    @Test
    public void testParseFileIsNull() {
        assertNull(Differ.generate(null, jsonFile2, "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void differTestDefault(String fileType) {
        String actual;
        if (fileType.equals("json")) {
            actual = Differ.generate(jsonFile1, jsonFile2);
        } else {
            actual = Differ.generate(yamlFile1, yamlFile2);
        }
        assertEquals(expectedStylish, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "json, stylish",
        "json, plain",
        "json, json",
        "yaml, stylish",
        "yaml, plain",
        "yaml, json",
    })
    public void differTest(String fileType, String formatter) {
        String actual;
        String expected;
        if (fileType.equals("json")) {
            actual = Differ.generate(jsonFile1, jsonFile2, formatter);
        } else {
            actual = Differ.generate(yamlFile1, yamlFile2, formatter);
        }
        expected = switch (formatter) {
            case "plain" -> expectedPlain;
            case "json" -> expectedJson;
            default -> expectedStylish;
        };
        assertEquals(expected, actual);
    }
}
