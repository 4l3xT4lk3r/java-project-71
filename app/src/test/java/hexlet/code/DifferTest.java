package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class DifferTest {
    private String jsonFile1;
    private String jsonFile2;
    private String yamlFile1;
    private String yamlFile2;
    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;
    @BeforeEach
    public void init() throws IOException {
        expectedStylish = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedStylish.txt")));
        expectedPlain = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedPlain.txt")));
        expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedJson.txt")));
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

    @Test
    public void differTestJsonDefault() {
        String actual = Differ.generate(jsonFile1, jsonFile2);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void differTestJsonStylish() {
        String actual = Differ.generate(jsonFile1, jsonFile2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void differTestJsonPlain() {
        String actual = Differ.generate(jsonFile1, jsonFile2, "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void differTestJsonJson() {
        String actual = Differ.generate(jsonFile1, jsonFile2, "json");
        assertEquals(expectedJson, actual);
    }
    @Test
    public void differTestYamlDefault() {
        String actual = Differ.generate(yamlFile1, yamlFile2);
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void differTestYamlStylish() {
        String actual = Differ.generate(yamlFile1, yamlFile2, "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void differTestYamlPlain() {
        String actual = Differ.generate(yamlFile1, yamlFile2, "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void differTestYamlJson() {
        String actual = Differ.generate(yamlFile1, yamlFile2, "json");
        assertEquals(expectedJson, actual);
    }
}
