package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, String> getMap(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        String fileData = Files.readString(path);

        ObjectMapper objectMapper;
        if (filePath.endsWith("yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.readValue(fileData, new TypeReference<>() { });
    }
}
