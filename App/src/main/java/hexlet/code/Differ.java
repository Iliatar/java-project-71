package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        try {
            Map<String, String> map1 = getJsonMap(filepath1);

            Map<String, String> map2 = getJsonMap(filepath2);


            String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                    .distinct()
                    .sorted().toArray(String[]::new);

            StringBuilder builder = new StringBuilder();
            builder.append("{\n");
            for (String key : keys) {
                if (map1.containsKey(key) && map2.containsKey(key)
                        && map1.get(key).equals(map2.get(key))) {
                    builder.append("    " + key + ": " + map1.get(key) + "\n");
                } else {
                    if (map1.containsKey(key)) {
                        builder.append("  - " + key + ": " + map1.get(key) + "\n");
                    }
                    if (map2.containsKey(key)) {
                        builder.append("  + " + key + ": " + map2.get(key) + "\n");
                    }
                }
            }
            builder.append("}");

            String result = builder.toString();
            System.out.println(result);
            return result;
        } catch (NoSuchFileException e) {
            throw new Exception("No such file: " + e.getMessage());
        } catch (MismatchedInputException e) {
            throw new Exception("Error parsing json: " + e.getMessage());
        } catch (JsonParseException e) {
            throw new Exception("Error parsing json: " + e.getMessage());
        }
    }

    private static Map<String, String> getJsonMap(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(filePath);
        String json = Files.readString(path);
        return objectMapper.readValue(json, new TypeReference<Map<String, String>>() { });
    }
}
