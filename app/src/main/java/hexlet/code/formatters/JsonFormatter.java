package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public final class JsonFormatter extends Formatter {
    private static final int ADD = 1;
    private static final int REMOVE = -1;
    private static final int UPDATE = -11;

    @Override
    public String format(TreeMap<String, TreeMap<Integer, Object>> diff) {
        List<String> list = diff.entrySet()
                .stream()
                .map(entry -> makeDiffString(entry.getKey(), entry.getValue()))
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        return "[\n" + String.join(",\n", list) + "\n]";
    }

    private String makeDiffString(String key, TreeMap<Integer, Object> changes) {
        Map<String, Object> mapForJsonString = new TreeMap<>();
        String changesSignature = changes.keySet().stream().map(Object::toString).collect(Collectors.joining());
        if (changesSignature.equals(Integer.toString(ADD))) {
            mapForJsonString.put("op", "add");
            mapForJsonString.put("value", changes.get(ADD));
        } else if (changesSignature.equals(Integer.toString(REMOVE))) {
            mapForJsonString.put("op", "remove");
            mapForJsonString.put("value", changes.get(REMOVE));
        } else if (changesSignature.equals(Integer.toString(UPDATE))) {
            mapForJsonString.put("op", "replace");
            mapForJsonString.put("value", changes.get(ADD));
        } else {
            return "";
        }
        mapForJsonString.put("path", key);
        String res;
        try {
            res = new ObjectMapper().writeValueAsString(mapForJsonString);
        } catch (JsonProcessingException exception) {
            return exception.getMessage();
        }
        return res;
    }
}
