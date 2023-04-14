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
        file1 = new File("src/test/resources/file1.json");
        file2 = new File("src/test/resources/file2.json");
        Parser.setMapper(mapper);
    }

    @Test
    public void testParseGoodJson() {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
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
        file1 = new File("src/test/resources/wrong.json");
        String actual;
        actual = Parser.parse(file1, file2);
        assertNull(actual);
    }

    @Test
    public void testParseGoodYaml() {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        file1 = new File("src/test/resources/file1.yml");
        file2 = new File("src/test/resources/file2.yml");
        Parser.setMapper(new YAMLMapper());
        String actual = Parser.parse(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseWrongYml() {
        file1 = new File("src/test/resources/wrong.yml");
        file2 = new File("src/test/resources/file2.yml");
        Parser.setMapper(new YAMLMapper());
        String actual;
        actual = Parser.parse(file1, file2);
        assertNull(actual);
    }
}
