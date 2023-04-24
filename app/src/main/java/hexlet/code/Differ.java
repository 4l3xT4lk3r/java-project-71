package hexlet.code;

import java.io.IOException;
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
        TreeMap<String, TreeMap<Integer, Object>> diff = MapDiffer.getDiffMap(file1, file2);
        return FormatterUtil.format(diff, formatter);
    }

    private static boolean isValidFile(String file) {
        return file.endsWith(".json") || file.endsWith("yml");
    }

}
