package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TreeMap;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String formatter) throws IOException {
        if (file1 == null || file2 == null) {
            return null;
        }
        String structureFromFile1 = readStringFromFile(file1);
        String structureFromFile2 = readStringFromFile(file2);
        String fileExtension1 = getFileExtension(file1);
        String fileExtension2 = getFileExtension(file2);
        TreeMap<String, Object> treeForStructure1 = Parser.mapData(structureFromFile1, fileExtension1);
        TreeMap<String, Object> treeForStructure2 = Parser.mapData(structureFromFile2, fileExtension2);
        TreeMap<String, HashMap<String, Object>> diff = Comparator.getDiffMap(treeForStructure1, treeForStructure2);
        return FormatterUtil.format(diff, formatter);
    }

    private static String readStringFromFile(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    private static String getFileExtension(String file) {
        String[] fileParts = file.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
