package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatterName) throws Exception {
        Map<String, Object> map1 = Parser.getMap(filepath1);
        Map<String, Object> map2 = Parser.getMap(filepath2);

        Map<String, Map<String, Object>> paramsDiffer = Comparer.getDifference(map1, map2);

        return Formatter.formatDiffer(paramsDiffer, formatterName);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
