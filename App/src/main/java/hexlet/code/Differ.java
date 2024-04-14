package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        try {
            Map<String, Object> map1 = Parser.getMap(filepath1);
            Map<String, Object> map2 = Parser.getMap(filepath2);
            String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                    .distinct()
                    .sorted().toArray(String[]::new);

            StringBuilder builder = new StringBuilder();
            builder.append("{\n");
            for (String key : keys) {
                if (map1.containsKey(key) && map2.containsKey(key)
                        && String.valueOf(map1.get(key)).equals(String.valueOf(map2.get(key)))) {
                    builder.append("    " + key + ": " + String.valueOf(map1.get(key)) + "\n");
                } else {
                    if (map1.containsKey(key)) {
                        builder.append("  - " + key + ": " + String.valueOf(map1.get(key)) + "\n");
                    }
                    if (map2.containsKey(key)) {
                        builder.append("  + " + key + ": " + String.valueOf(map2.get(key)) + "\n");
                    }
                }
            }
            builder.append("}");

            return builder.toString();
        } catch (NoSuchFileException e) {
            return "No such file: " + e.getMessage();
        } catch (MismatchedInputException e) {
            return "Error parsing file: " + e.getMessage();
        } catch (JsonParseException e) {
            return "Error parsing file: " + e.getMessage();
        }
    }
}
