package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.TreeMap;

public final class JsonFormatter implements Formatter {

    @Override
    public String format(TreeMap<String, HashMap<String, Object>> diff) throws JsonProcessingException {
        ObjectMapper mapper  = new JsonMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}
