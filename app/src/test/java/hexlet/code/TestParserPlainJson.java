package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import hexlet.code.formatters.Formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParserPlainJson {
    private File file1;
    private File file2;
    private String expected;
    private Formatter formatter;

    @BeforeEach
    public void init() {
        formatter = new Stylish();
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
        Parser.setMapper(new ObjectMapper());
    }

    @Test
    public void testParseGoodPlainJson() {
        String actual = Parser.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseWrongJson() {
        file1 = new File("src/test/resources/plainWrongJsonFile.json");
        String actual = Parser.parse(file1, file2, formatter);
        expected = "Unexpected character ('\"' (code 34)): was expecting comma to separate Object entries\n"
                + " at [Source: (File); line: 3, column: 4]";
        assertEquals(expected, actual);
    }
}
