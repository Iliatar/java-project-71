package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Objects;

public class Comparer {
    public static Map<String, DifferItem> getDifference(Map<String, Object> map1,
                                                                   Map<String, Object> map2) {
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());

        Map<String, DifferItem> differs = new TreeMap<>();

        for (String key : keySet) {
            DifferItem differItem = new DifferItem();
            differs.put(key, differItem);

            if (!map1.containsKey(key)) {
                differItem.setStatusName(DifferItem.ADDED);
                differItem.setNewValue(map2.get(key));
            } else if (!map2.containsKey(key)) {
                differItem.setStatusName(DifferItem.DELETED);
                differItem.setOldValue(map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                differItem.setStatusName(DifferItem.UNCHANGED);
                differItem.setNewValue(map2.get(key));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                differItem.setStatusName(DifferItem.CHANGED);
                differItem.setNewValue(map2.get(key));
                differItem.setOldValue(map1.get(key));
            }
        }

        return differs;
    }
}
