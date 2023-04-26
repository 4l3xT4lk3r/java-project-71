package hexlet.code;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Comparator {
    public static TreeMap<String, HashMap<String, Object>> getDiffMap(TreeMap<String, Object> map1,
                                                                      TreeMap<String, Object> map2) {
        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        TreeMap<String, HashMap<String, Object>> res = new TreeMap<>();
        keySet.forEach(key -> res.put(key, getRecs(key, map1, map2)));
        return res;
    }

    private static HashMap<String, Object> getRecs(String key,
                                                   TreeMap<String, Object> map1,
                                                   TreeMap<String, Object> map2) {
        HashMap<String, Object> res = new HashMap<>();
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        if (!map2.containsKey(key)) {
            res.put("STATUS", "REMOVED");
            res.put("OLD_VALUE", map1.get(key));
        } else if (!map1.containsKey(key)) {
            res.put("STATUS", "ADDED");
            res.put("NEW_VALUE", map2.get(key));
        } else {
            if (Objects.equals(value1, value2)) {
                res.put("STATUS", "SAME");
                res.put("OLD_VALUE", map1.get(key));
            } else if (value1 == null || value2 == null) {
                res.put("STATUS", "UPDATED");
                res.put("OLD_VALUE", map1.get(key));
                res.put("NEW_VALUE", map2.get(key));
            } else if (value1.toString().equals(value2.toString())) {
                res.put("STATUS", "SAME");
                res.put("OLD_VALUE", map1.get(key));
            } else {
                res.put("STATUS", "UPDATED");
                res.put("OLD_VALUE", map1.get(key));
                res.put("NEW_VALUE", map2.get(key));
            }
        }
        return res;
    }
}
