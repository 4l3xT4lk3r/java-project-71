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
        String res = switch ((String) changes.get("STATUS")) {
            case "ADDED" -> String.format("\s\s+ %s: %s", key, changes.get("NEW_VALUE"));
            case "REMOVED" -> String.format("\s\s- %s: %s", key, changes.get("OLD_VALUE"));
            case "UPDATED" -> String.format(String.format("\s\s- %s: %s", key, changes.get("OLD_VALUE")))
                    + "\n"
                    + String.format("\s\s+ %s: %s", key, changes.get("NEW_VALUE"));
            default -> String.format("\s\s\s\s%s: %s", key, changes.get("OLD_VALUE"));
        };
        return res;
    }
}
