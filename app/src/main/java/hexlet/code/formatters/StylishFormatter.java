package hexlet.code.formatters;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.stream.Collectors;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(TreeMap<String, HashMap<String, Object>> diff) {
        List<String> list = diff.entrySet()
                .stream()
                .map(entry -> makeDiffString(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return "{\n" + String.join("\n", list) + "\n}";
    }

    private String makeDiffString(String key, HashMap<String, Object> changes) {
        String res;
        if (changes.get("STATUS").equals("ADDED")) {
            res = String.format("\s\s+ %s: %s", key, changes.get("NEW_VALUE"));
        } else if (changes.get("STATUS").equals("REMOVED")) {
            res = String.format("\s\s- %s: %s", key, changes.get("OLD_VALUE"));
        } else if (changes.get("STATUS").equals("UPDATED")) {
            res = String.format(String.format("\s\s- %s: %s", key, changes.get("OLD_VALUE")))
                    + "\n"
                    + String.format("\s\s+ %s: %s", key, changes.get("NEW_VALUE"));
        } else {
            res = String.format("\s\s\s\s%s: %s", key, changes.get("OLD_VALUE"));
        }
        return res;
    }
}
