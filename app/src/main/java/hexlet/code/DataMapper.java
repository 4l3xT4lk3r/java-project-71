package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.TreeMap;

public class DataMapper {
    private static ObjectMapper getMapper(String file) {
        if (file.endsWith(".yml")) {
            return new YAMLMapper();
        }
        return new ObjectMapper();
    }
    public static Path stringToPath(String file) {
        return Path.of(file);
    }

    public static TreeMap<String, Object> mapData(String file) throws IOException {
        Path path = stringToPath(file);
        TreeMap<String, Object> map = getMapper(file).readValue(path.toFile(), new TypeReference<>() {
        });
        return map;
    }


}
