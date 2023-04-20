package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParserNestedJsonPlain {
    private File file1;
    private File file2;
    private String expected;
    private Formatter formatter;

    @BeforeEach
    public void init() {
        formatter = new PlainFormatter();
        file1 = new File("src/test/resources/plainJsonFile1.json");
        file2 = new File("src/test/resources/plainJsonFile2.json");
        expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
    }

    @Test
    public void testParseGoodNestedJson() {
        file1 = new File("src/test/resources/nestedJsonFile1.json");
        file2 = new File("src/test/resources/nestedJsonFile2.json");
        Parser.setMapper(new ObjectMapper());
        String actual = Parser.parse(file1, file2, formatter);
        assertEquals(expected, actual);
    }
}
