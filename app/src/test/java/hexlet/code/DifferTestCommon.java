package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class DifferTestCommon {

    private String file2;

    @BeforeEach
    public void init() {
        file2 = "src/test/resources/nestedJsonFile1.json";
    }

    @Test
    public void testParseFileNotExist() {
        String actual = Differ.generate("FileNotExist.json", file2, "stylish");
        assertEquals("FileNotExist.json (No such file or directory)", actual);
    }
    @Test
    public void testParseFileIsNull() {
        assertNull(Differ.generate(null, file2, "plain"));
    }
}
