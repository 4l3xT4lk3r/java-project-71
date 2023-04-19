package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParserPlainYaml {
    private File file1;
    private File file2;
    private String expected;
    private Formatter formatter;
    private ObjectMapper mapper = new YAMLMapper();

    @BeforeEach
    public void init() {
        formatter = new Stylish();
        file1 = new File("src/test/resources/plainYamlFile1.yml");
        file2 = new File("src/test/resources/plainYamlFile2.yml");
        expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        Parser.setMapper(mapper);
    }

    @Test
    public void testParseGoodPlainYaml() {
        Parser.setMapper(new YAMLMapper());
        String actual = Parser.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseGoodEqualYaml() {
        Parser.setMapper(new YAMLMapper());
        String actual = Parser.parse(file1, file1, formatter);
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
        expected = "File src/test/resources/plainYamlWrongFile.yml is corrupted!";
        String actual = Parser.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }
}
