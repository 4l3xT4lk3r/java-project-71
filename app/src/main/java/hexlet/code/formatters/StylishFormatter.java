package hexlet.code.formatters;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class StylishFormatter extends Formatter {

    private static final int ADD = 1;
    private static final int REMOVE = -1;
    private static final int UPDATE = -11;
    private static final int SAME = 0;

    @Override
    public String format(TreeMap<String, TreeMap<Integer, Object>> diff) {
        List<String> list = diff.entrySet()
                .stream()
                .map(entry -> makeDiffString(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return "{\n" + String.join("\n", list) + "\n}";
    }
    private String makeDiffString(String key, TreeMap<Integer, Object> changes) {
        String res;
        String changesSignature = changes.keySet().stream().map(Object::toString).collect(Collectors.joining());
        if (changesSignature.equals(Integer.toString(ADD))) {
            res = String.format("\s\s+ %s: %s", key, changes.get(ADD));
        } else if (changesSignature.equals(Integer.toString(REMOVE))) {
            res = String.format("\s\s- %s: %s", key, changes.get(REMOVE));
        } else if (changesSignature.equals(Integer.toString(UPDATE))) {
            res = String.format(String.format("\s\s- %s: %s", key, changes.get(REMOVE)))
                    + "\n"
                    + String.format("\s\s+ %s: %s", key, changes.get(ADD));
        } else {
            res = String.format("\s\s\s\s%s: %s", key, changes.get(SAME));
        }
        return res;
    }
}
