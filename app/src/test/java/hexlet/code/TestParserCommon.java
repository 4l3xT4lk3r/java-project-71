package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParserCommon {

    @BeforeEach
    public void init() {
        Parser.setMapper(new ObjectMapper());
    }

    @Test
    public void testParseFileNotExist() {
        File file1 = new File("FileNotExist.json");
        File file2 = new File("src/test/resources/nestedJsonFile1.json");
        String actual = Parser.parse(file1, file2, new StylishFormatter());
        assertEquals("FileNotExist.json (No such file or directory)", actual);
    }

    @Test
    public void testParseFileIsNull() {
        File file1 = null;
        File file2 = new File("src/test/resources/nestedJsonFile1.json");
        String actual = Parser.parse(file1, file2, new PlainFormatter());
        assertEquals("argument \"src\" is null", actual);
    }
}
