package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Comparer {
    public static Map<String, Map<String, Object>> getDifference(Map<String, Object> map1,
                                                                   Map<String, Object> map2) {
        String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .toArray(String[]::new);

        Map<String, Map<String, Object>> differs = new HashMap<>(keys.length);

        for (String key : keys) {
            Map<String, Object> differ = new HashMap<>();
            differs.put(key, differ);

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

        return differs;
    }
}
