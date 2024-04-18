package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatterName) {
        try {
            Map<String, Object> map1 = Parser.getMap(filepath1);
            Map<String, Object> map2 = Parser.getMap(filepath2);
            String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                    .distinct()
                    .sorted().toArray(String[]::new);

            List<Map<String, String>> paramsDiffer = new ArrayList<>(keys.length);

            for (String key : keys) {
                Map<String, String> differ = new HashMap<>();
                paramsDiffer.add(differ);
                differ.put("key", key);

                if (map1.containsKey(key) && map2.containsKey(key)) {
                    if (String.valueOf(map1.get(key)).equals(String.valueOf(map2.get(key)))) {
                        differ.put("value", String.valueOf(map1.get(key)));
                        differ.put("type", getObjectType(map1.get(key)));
                        differ.put("action", "equal");
                    } else {
                        differ.put("oldValue", String.valueOf(map1.get(key)));
                        differ.put("oldType", getObjectType(map1.get(key)));
                        differ.put("newValue", String.valueOf(map2.get(key)));
                        differ.put("newType", getObjectType(map2.get(key)));
                        differ.put("action", "change");
                    }
                } else if (map1.containsKey(key)) {
                    differ.put("oldValue", String.valueOf(map1.get(key)));
                    differ.put("oldType", getObjectType(map1.get(key)));
                    differ.put("action", "remove");
                } else {
                    differ.put("newValue", String.valueOf(map2.get(key)));
                    differ.put("newType", getObjectType(map2.get(key)));
                    differ.put("action", "add");
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

    private static String getObjectType(Object o) {
        String type = "Object";

        if (o instanceof String) {
            type = "String";
        } else if (o instanceof Number) {
            type = "Number";
        } else if (o instanceof Boolean) {
            type = "Boolean";
        }

        return type;
    }
}
