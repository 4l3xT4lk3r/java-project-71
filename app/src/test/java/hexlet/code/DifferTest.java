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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class DifferTest {
    private static String file1;
    private static String file2;
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
        file1 = "src/test/resources/nestedFile1";
        file2 = "src/test/resources/nestedFile2";
    }

    @Test
    public void differTestFileNotExist() {
        IOException thrown = assertThrows(
                IOException.class,
                () -> Differ.generate("FileNotExist.json", file2 + ".json", "stylish")

        );
        assertTrue(thrown.getMessage().contentEquals("FileNotExist.json"));
    }

    @Test
    public void differTestFileIsNull() throws IOException {
        assertNull(Differ.generate(null, file2 + ".json", "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void differTestDefault(String fileType) throws IOException {
        String actual;
        actual = Differ.generate(file1 + "." + fileType, file2 + "." + fileType);
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
    public void differTest(String fileType, String formatter) throws IOException {
        String actual;
        String expected;
        actual = Differ.generate(file1 + "." + fileType, file2 + "." + fileType, formatter);
        expected = switch (formatter) {
            case "plain" -> expectedPlain;
            case "json" -> expectedJson;
            default -> expectedStylish;
        };
        assertEquals(expected, actual);
    }
}
