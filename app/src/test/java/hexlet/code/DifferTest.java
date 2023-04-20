package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private File file1;
    private File file2;
    private String expected;
    private Formatter formatter;
    @BeforeEach
    public void init() {
        Differ.setMapper(new ObjectMapper());
        formatter = new StylishFormatter();
        file1 = new File("src/test/resources/plainJsonFile1.json");
        file2 = new File("src/test/resources/plainJsonFile2.json");
        expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        Differ.setMapper(new ObjectMapper());
    }
    @Test
    public void testParseFileNotExist() {
        file1 = new File("FileNotExist.json");
        String actual = Differ.parse(file1, file2, new StylishFormatter());
        assertEquals("FileNotExist.json (No such file or directory)", actual);
    }

    @Test
    public void testParseFileIsNull() {
        file2 = new File("src/test/resources/nestedJsonFile1.json");
        String actual = Differ.parse(null, file2, new PlainFormatter());
        assertEquals("argument \"src\" is null", actual);
    }
    @Test
    public void testParseGoodPlainJson() {
        String actual = Differ.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseWrongJson() {
        file1 = new File("src/test/resources/plainWrongJsonFile.json");
        String actual = Differ.parse(file1, file2, formatter);
        expected = "Unexpected character ('\"' (code 34)): was expecting comma to separate Object entries\n"
                + " at [Source: (File); line: 3, column: 4]";
        assertEquals(expected, actual);
    }
    @Test
    public void testParseGoodPlainYaml() {
        expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        file1 = new File("src/test/resources/plainYamlFile1.yml");
        file2 = new File("src/test/resources/plainYamlFile2.yml");
        Differ.setMapper(new YAMLMapper());
        String actual = Differ.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }
    @Test
    public void testParseGoodEqualYaml() {
        Differ.setMapper(new YAMLMapper());
        String actual = Differ.parse(file1, file1, formatter);
        expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";
        assertEquals(expected, actual);
    }
    @Test
    public void testParseWrongYaml() {
        file1 = new File("src/test/resources/plainYamlWrongFile.yml");
        file2 = new File("src/test/resources/plainYamlFile2.yml");
        expected = """
                mapping values are not allowed here
                 in 'reader', line 2, column 12:
                        verbose: true
                               ^

                 at [Source: (File); line: 2, column: 12]""";
        Differ.setMapper(new YAMLMapper());
        String actual = Differ.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }
}
