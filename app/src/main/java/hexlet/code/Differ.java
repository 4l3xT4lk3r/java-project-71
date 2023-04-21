package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {
    private static final int ADD = 1;
    private static final int REMOVE = -1;
    private static final int SAME = 0;

    private static ObjectMapper mapper;

    public static void setMapper(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    public static String generate(String file1, String file2) {
        TreeMap<String, Object> map1;
        TreeMap<String, Object> map2;
        try {
            map1 = mapper.readValue(new File(file1), new TypeReference<>() {
            });
            map2 = mapper.readValue(new File(file2), new TypeReference<>() {
            });
        }catch (NullPointerException | IllegalArgumentException | IOException exception){
            return exception.getMessage();
        }
        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        TreeMap<String, TreeMap<Integer, Object>> diff = new TreeMap<>();
        keySet.forEach(key -> diff.put(key, getRecs(key, map1, map2)));
        return new StylishFormatter().format(diff);
    }

    public static String generate(String file1, String file2, String formatter) {
        TreeMap<String, Object> map1;
        TreeMap<String, Object> map2;
        try {
            map1 = mapper.readValue(new File(file1), new TypeReference<>() {
            });
            map2 = mapper.readValue(new File(file2), new TypeReference<>() {
            });
        }catch (NullPointerException | IllegalArgumentException | IOException exception){
            return exception.getMessage();
        }
        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        TreeMap<String, TreeMap<Integer, Object>> diff = new TreeMap<>();
        keySet.forEach(key -> diff.put(key, getRecs(key, map1, map2)));
        return getFormatter(formatter).format(diff);
    }

    private static TreeMap<Integer, Object> getRecs(String k, TreeMap<String, Object> m1, TreeMap<String, Object> m2) {
        TreeMap<Integer, Object> res = new TreeMap<>();
        if (!m2.containsKey(k)) {
            res.put(REMOVE, m1.get(k));
        } else if (!m1.containsKey(k)) {
            res.put(ADD, m2.get(k));
        } else if (m2.containsKey(k) && m1.containsKey(k)) {
            Object v1 = m1.get(k);
            Object v2 = m2.get(k);
            v1 = convertNull(v1);
            v2 = convertNull(v2);
            if (v1.toString().equals(v2.toString())) {
                res.put(SAME, m1.get(k));
            } else {
                res.put(REMOVE, m1.get(k));
                res.put(ADD, m2.get(k));
            }
        }
        return res;
    }

    private static Object convertNull(Object object) {
        return object == null ? "null" : object;
    }

    private static Formatter getFormatter(String formatterName) {
        Formatter formatter;
        switch (formatterName) {
            case "plain":
                formatter = new PlainFormatter();
                break;
            case "json":
                formatter = new JsonFormatter();
                break;
            default:
                formatter = new StylishFormatter();
        }
        return formatter;
    }
}
