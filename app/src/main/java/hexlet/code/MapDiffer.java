package hexlet.code;

import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapDiffer {
    private static final int ADD = 1;
    private static final int REMOVE = -1;
    private static final int SAME = 0;

    public static TreeMap<String, TreeMap<Integer, Object>> getDiffMap(String file1, String file2) throws IOException {
        TreeMap<String, Object> map1 = DataMapper.mapData(file1);
        TreeMap<String, Object> map2 = DataMapper.mapData(file2);
        TreeSet<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(new TreeSet<>(map2.keySet()));
        TreeMap<String, TreeMap<Integer, Object>> res = new TreeMap<>();
        keySet.forEach(key -> res.put(key, getRecs(key, map1, map2)));
        return res;
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
            if (v1 == null && v2 == null) {
                res.put(SAME, m1.get(k));
            } else if (v1 == null || v2 == null) {
                res.put(REMOVE, m1.get(k));
                res.put(ADD, m2.get(k));
            } else if (v1.toString().equals(v2.toString())) {
                res.put(SAME, m1.get(k));
            } else {
                res.put(REMOVE, m1.get(k));
                res.put(ADD, m2.get(k));
            }
        }
        return res;
    }
}
