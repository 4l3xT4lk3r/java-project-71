package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class FormatterUtil {
    private static final Map<String, Formatter> FORMATTERS = new HashMap<>();

    static {
        FORMATTERS.put("json", new JsonFormatter());
        FORMATTERS.put("stylish", new StylishFormatter());
        FORMATTERS.put("plain", new PlainFormatter());
    }

    public static String format(TreeMap<String, TreeMap<Integer, Object>> map, String formatter) {
        return FORMATTERS.get(formatter).format(map);

    }
}
