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
    public static String generate(String filepath1, String filepath2, String formatterName) throws Exception {
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
                        differ.put("type", "equal");
                    } else {
                        differ.put("oldValue", String.valueOf(map1.get(key)));
                        differ.put("newValue", String.valueOf(map2.get(key)));
                        differ.put("type", "change");
                    }
                } else if (map1.containsKey(key)) {
                    differ.put("oldValue", String.valueOf(map1.get(key)));
                    differ.put("type", "remove");
                } else {
                    differ.put("newValue", String.valueOf(map2.get(key)));
                    differ.put("type", "add");
                }
            }

            String result;
            switch (formatterName) {
                case "stylish":
                    result = StylishFormatter.formatDiffer(paramsDiffer);
                    break;
                default:
                    result = "";
            }
            return result;
        } catch (NoSuchFileException e) {
            return "No such file: " + e.getMessage();
        } catch (MismatchedInputException e) {
            return "Error parsing file: " + e.getMessage();
        } catch (JsonParseException e) {
            return "Error parsing file: " + e.getMessage();
        }
    }
}
