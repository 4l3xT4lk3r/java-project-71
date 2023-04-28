package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            String value = prepareValue(changes.get("NEW_VALUE"));
            res = String.format("Property '%s' was added with value: %s", key, value);
        } else if (changes.get("STATUS").equals("REMOVED")) {
            res = String.format("Property '%s' was removed", key);
        } else if (changes.get("STATUS").equals("UPDATED")) {
            String valueRemove = prepareValue(changes.get("OLD_VALUE"));
            String valueAdd = prepareValue(changes.get("NEW_VALUE"));
            res = String.format("Property '%s' was updated. From %s to %s", key, valueRemove, valueAdd);
        } else {
            res = "";
        }
        return res;
    }

    private boolean isValueComplex(Object object) {
        return object instanceof Map || object instanceof List;
    }

    private String prepareValue(Object value) {
        String res = (value == null) ? null : value.toString();
        if (value instanceof String) {
            res = "'" + value + "'";
        } else if (isValueComplex(value)) {
            res = "[complex value]";
        }
        return res;
    }
}
