package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTestPlainYaml {
    private String file1;
    private String file2;
    private String expected;

    @BeforeEach
    public void init() {
        file1 = "src/test/resources/plainYamlFile1.yml";
        file2 = "src/test/resources/plainYamlFile2.yml";
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
    public void testParseGoodPlainYaml() {
        String actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testParseGoodEqualYaml() {
        String actual = Differ.generate(file1, file1);
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
        expected = """
                mapping values are not allowed here
                 in 'reader', line 2, column 12:
                        verbose: true
                               ^

                 at [Source: (File); line: 2, column: 12]""";
        String actual = Differ.generate("src/test/resources/plainYamlWrongFile.yml", file2);
        assertEquals(expected, actual);
    }
}
