package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatterName) {
        try {
            Map<String, Object> map1 = Parser.getMap(filepath1);
            Map<String, Object> map2 = Parser.getMap(filepath2);
            String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                    .distinct()
                    .toArray(String[]::new);

            Map<String, Map<String, Object>> paramsDiffer = new HashMap<>(keys.length);

            for (String key : keys) {
                Map<String, Object> differ = new HashMap<>();
                paramsDiffer.put(key, differ);

                if (map1.containsKey(key) && map2.containsKey(key)) {
                    if ((map1.get(key) == null && map2.get(key) == null)
                            || (map1.get(key) != null && map1.get(key).equals(map2.get(key)))) {
                        differ.put("sameValue", map1.get(key));
                    } else {
                        differ.put("oldValue", map1.get(key));
                        differ.put("newValue", map2.get(key));
                    }
                } else if (map1.containsKey(key)) {
                    differ.put("oldValue", map1.get(key));
                } else {
                    differ.put("newValue", map2.get(key));
                }
            }

            return Formatter.formatDiffer(paramsDiffer, formatterName);
        } catch (NoSuchFileException e) {
            return "No such file: " + e.getMessage();
        } catch (MismatchedInputException e) {
            return "Error parsing file: " + e.getMessage();
        } catch (JsonParseException e) {
            return "Error parsing file: " + e.getMessage();
        } catch (Exception e) {
            return  "Error: " + e.getMessage();
        }
    }

    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, "stylish");
    }
}
