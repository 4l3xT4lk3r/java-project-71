package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(TreeMap<String, HashMap<String, Object>> diff) {
        List<String> res = diff.entrySet()
                .stream()
                .map(entry -> makeDiffString(entry.getKey(), entry.getValue()))
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        return String.join("\n", res);
    }

    private String makeDiffString(String key, HashMap<String, Object> changes) {
        String res;
        if (changes.get("STATUS").equals("ADDED")) {
            Object value = prepareValue(changes.get("NEW_VALUE"));
            res = String.format("Property '%s' was added with value: %s", key, value);
        } else if (changes.get("STATUS").equals("REMOVED")) {
            res = String.format("Property '%s' was removed", key);
        } else if (changes.get("STATUS").equals("UPDATED")) {
            Object valueRemove = prepareValue(changes.get("OLD_VALUE"));
            Object valueAdd = prepareValue(changes.get("NEW_VALUE"));
            res = String.format("Property '%s' was updated. From %s to %s", key, valueRemove, valueAdd);
        } else {
            res = "";
        }
        return res;
    }

    private boolean isValueComplex(Object object) {
        boolean res = true;
        if (object instanceof String) {
            res = false;
        } else if (object instanceof Integer || object instanceof Long) {
            res = false;
        } else if (object instanceof Float || object instanceof Double) {
            res = false;
        } else if (object instanceof Boolean) {
            res = false;
        } else if (object == null) {
            res = false;
        }
        return res;
    }

    private Object prepareValue(Object value) {
        Object res = value;
        if (value instanceof String) {
            res = "'" + value + "'";
        }
        if (isValueComplex(value)) {
            res = "[complex value]";
        }
        return res;
    }
}
