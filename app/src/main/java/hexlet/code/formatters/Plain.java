package hexlet.code.formatters;

import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public final class Plain extends Formatter {
    @Override
    public String format(TreeMap<String, TreeMap<Integer, Object>> diff) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, TreeMap<Integer, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            TreeMap<Integer, Object> changes = entry.getValue();
            String line = makeString(key, changes);
            if (!line.equals("")) {
                list.add(line);
            }
        }
        return String.join("\n", list);
    }

    private String makeString(String key, TreeMap<Integer, Object> changes) {
        int size = changes.size();
        StringBuilder result;
        if (size == 1) {
            result = new StringBuilder("Property '" + key + "' was ");
        } else {
            result = new StringBuilder("Property '" + key + "' was updated. From ");
        }
        for (Map.Entry<Integer, Object> change : changes.entrySet()) {
            if (change.getKey() == 0) {
                result = new StringBuilder();
                continue;
            }
            Object object = change.getValue();
            if (isObjectComplex(object)) {
                object = "[complex value]";
            } else {
                if (object instanceof String) {
                    object = "'" + object + "'";
                }
            }
            if (changes.size() == 1) {
                if (change.getKey() == 1) {
                    result.append("was added with value: ").append(object);
                } else {
                    result.append("was removed");
                }
            } else {
                if (change.getKey() == -1) {
                    result.append(object).append(" to ");
                } else {
                    result.append(object);
                }
            }
        }
        return result.toString();
    }

    private boolean isObjectComplex(Object object) {
        if (object instanceof String) {
            return false;
        } else if (object instanceof Integer || object instanceof Long) {
            return false;
        } else if (object instanceof Float || object instanceof Double) {
            return false;
        } else if (object instanceof Boolean) {
            return false;
        }
        return true;
    }
}

