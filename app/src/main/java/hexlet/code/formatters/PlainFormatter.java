package hexlet.code.formatters;

import java.util.TreeMap;
import java.util.List;
import java.util.stream.Collectors;

public final class PlainFormatter extends Formatter {

    private static final int ADD = 1;
    private static final int REMOVE = -1;
    private static final int UPDATE = -11;

    @Override
    public String format(TreeMap<String, TreeMap<Integer, Object>> diff) {
        List<String> res = diff.entrySet()
                .stream()
                .map(entry -> makeDiffString(entry.getKey(), entry.getValue()))
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        return String.join("\n", res);
    }

    private String makeDiffString(String key, TreeMap<Integer, Object> changes) {
        String res;
        String changesSignature = changes.keySet().stream().map(Object::toString).collect(Collectors.joining());
        if (changesSignature.equals(Integer.toString(ADD))) {
            Object value = prepareValue(changes.get(ADD));
            res = String.format("Property '%s' was added with value: %s", key, value);
        } else if (changesSignature.equals(Integer.toString(REMOVE))) {
            res = String.format("Property '%s' was removed", key);
        } else if (changesSignature.equals(Integer.toString(UPDATE))) {
            Object valueRemove = prepareValue(changes.get(REMOVE));
            Object valueAdd = prepareValue(changes.get(ADD));
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
