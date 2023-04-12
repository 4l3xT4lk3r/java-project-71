package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class TestDiffer {
    private File file1;
    private File file2;

    @BeforeEach
    public void initFiles() {
        file1 = new File("src/test/resources/file1.json");
        file2 = new File("src/test/resources/file2.json");
    }

    @Test
    public void testGenerateOne() {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "- verbose: true\n"
                + "}";
        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateFileNotExist() {
        file2 = new File("FileNotExist.json");
        String actual;
        actual = Differ.generate(file1, file2);
        assertNull(actual);
    }

    @Test
    public void testGenerateWrongJson() {
        file1 = new File("src/test/resources/wrong.json");
        String actual;
        actual = Differ.generate(file1, file2);
        assertNull(actual);
    }

}
