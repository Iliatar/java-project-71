package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Comparer {
    public static Map<String, DifferItem> getDifference(Map<String, Object> map1,
                                                                   Map<String, Object> map2) {
        String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .toArray(String[]::new);

        Map<String, DifferItem> differs = new HashMap<>(keys.length);

        for (String key : keys) {
            DifferItem differItem = new DifferItem();
            differs.put(key, differItem);

            if (map1.containsKey(key) && map2.containsKey(key)
                    && ((map1.get(key) == null && map2.get(key) == null)
                    || (map1.get(key) != null && map1.get(key).equals(map2.get(key))))) {
                differItem.setStatusName(DifferItem.UNCHANGED);
                differItem.setNewValue(map2.get(key));
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                differItem.setStatusName(DifferItem.CHANGED);
                differItem.setNewValue(map2.get(key));
                differItem.setOldValue(map1.get(key));
            } else if (map1.containsKey(key)) {
                differItem.setStatusName(DifferItem.DELETED);
                differItem.setOldValue(map1.get(key));
            } else {
                differItem.setStatusName(DifferItem.ADDED);
                differItem.setNewValue(map2.get(key));
            }
        }

        return differs;
    }
}
