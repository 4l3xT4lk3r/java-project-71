package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTestPlainJson {
    private String file1;
    private String file2;
    private String expected;
    @BeforeEach
    public void init() {
        file1 = "src/test/resources/plainJsonFile1.json";
        file2 = "src/test/resources/plainJsonFile2.json";
        expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
    }

    @Test
    public void testParseGoodPlainJson() {
        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }
    @Test
    public void testParseWrongJson() {
        String actual = Differ.generate("src/test/resources/plainWrongJsonFile.json", file2, "stylish");
        expected = "Unexpected character ('\"' (code 34)): was expecting comma to separate Object entries\n"
                + " at [Source: (File); line: 3, column: 4]";
        assertEquals(expected, actual);
    }
}
