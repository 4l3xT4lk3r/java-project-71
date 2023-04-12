package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class Differ {
    public static String generate(File json1, File json2) {
        if (json1 == null || json2 == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1;
        Map<String, Object> map2;
        try {
            map1 = mapper.readValue(json1, new TypeReference<TreeMap<String, Object>>() {
            });
            map2 = mapper.readValue(json2, new TypeReference<TreeMap<String, Object>>() {
            });
        } catch (IOException exception) {
            return null;
        }

        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        List<String> list = new ArrayList<>();
        for (String key : keySet) {
            if (!map2.containsKey(key)) {
                list.add("- " + key + ": " + map1.get(key).toString());
            } else if (!map1.containsKey(key)) {
                list.add("- " + key + ": " + map2.get(key).toString());
            } else if (map2.containsKey(key) && map1.containsKey(key)) {
                Object v1 = map1.get(key);
                Object v2 = map2.get(key);
                if (v1.toString().equals(v2.toString())) {
                    list.add("  " + key + ": " + map1.get(key).toString());
                } else {
                    list.add("- " + key + ": " + map1.get(key).toString());
                    list.add("+ " + key + ": " + map2.get(key).toString());
                }
            }
        }
        return "{\n" + String.join("\n", list) + "\n}";
    }
}
