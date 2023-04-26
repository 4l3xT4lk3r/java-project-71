package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.TreeMap;

public class Parser {

    public static TreeMap<String, Object> mapData(String string, String fileExtension) throws IOException {
        ObjectMapper mapper;

        if (fileExtension.equals("yml") || fileExtension.equals("yaml")) {
            mapper = new YAMLMapper();
        } else {
            mapper = new JsonMapper();
        }
        return mapper.readValue(string, new TypeReference<>() {
        });
    }

}
