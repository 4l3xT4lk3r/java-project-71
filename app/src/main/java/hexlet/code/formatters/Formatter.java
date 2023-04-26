package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.TreeMap;

public interface Formatter {
    String format(TreeMap<String, HashMap<String, Object>> diff) throws JsonProcessingException;
}
