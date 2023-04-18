package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Parser {

    private static ObjectMapper mapper;

    public static void setMapper(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    public static String parse(File file1, File file2, Formatter formatter) {
        if (file1 == null || file2 == null) {
            return null;
        }
        Map<String, Object> map1;
        Map<String, Object> map2;
        try {
            map1 = mapper.readValue(file1, new TypeReference<TreeMap<String, Object>>() {
            });
            map2 = mapper.readValue(file2, new TypeReference<TreeMap<String, Object>>() {
            });
        } catch (IOException exception) {
            return null;
        }
        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        TreeMap<String, TreeMap<Integer, Object>> diff = new TreeMap<>();
        for (String key : keySet) {
            TreeMap<Integer, Object> changes = new TreeMap<>();
            if (!map2.containsKey(key)) {
                changes.put(-1, map1.get(key));
            } else if (!map1.containsKey(key)) {
                changes.put(1, map2.get(key));
            } else if (map2.containsKey(key) && map1.containsKey(key)) {
                Object v1 = map1.get(key);
                Object v2 = map2.get(key);
                if (v1 == null) {
                    v1 = "null";
                }
                if (v2 == null) {
                    v2 = "null";
                }
                if (v1.toString().equals(v2.toString())) {
                    changes.put(0, map1.get(key));
                } else {
                    changes.put(-1, map1.get(key));
                    changes.put(1, map2.get(key));
                }
            }
            diff.put(key, changes);
        }
        return formatter.format(diff);
    }
}
