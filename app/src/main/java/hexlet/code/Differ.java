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
        if (!isValidFile(file1) || !isValidFile(file2)) {
            return "Wrong file! Check extension!";
        }
        String string1 = readStringFromFile(file1);
        String string2 = readStringFromFile(file2);
        String fileExt1 = getFileExtension(file1);
        String fileExt2 = getFileExtension(file2);
        TreeMap<String, Object> map1 = Parser.mapData(string1, fileExt1);
        TreeMap<String, Object> map2 = Parser.mapData(string2, fileExt2);
        TreeMap<String, HashMap<String, Object>> diff = Comparator.getDiffMap(map1, map2);
        return FormatterUtil.format(diff, formatter);
    }

    private static boolean isValidFile(String file) {
        return file.endsWith(".json") || file.endsWith("yml");
    }

    private static String readStringFromFile(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    private static String getFileExtension(String file) {
        String[] fileParts = file.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
