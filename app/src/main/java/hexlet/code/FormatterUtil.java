package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class FormatterUtil {
    private static final Map<String, Formatter> FORMATTERS = Map.of(
            "json", new JsonFormatter(),
            "stylish", new StylishFormatter(),
            "plain", new PlainFormatter());

    public static String format(TreeMap<String, HashMap<String, Object>> map,
                                String formatter) throws JsonProcessingException {
        return FORMATTERS.get(formatter).format(map);
    }
}
