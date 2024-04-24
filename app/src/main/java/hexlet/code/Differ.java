package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatterName) throws Exception {
        Map<String, Object> map1 = parse(filepath1);
        Map<String, Object> map2 = parse(filepath2);

        Map<String, DifferItem> paramsDiffer = Comparer.getDifference(map1, map2);

        return Formatter.formatDiffer(paramsDiffer, formatterName);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        String fileData = Files.readString(path);
        String format = filePath.substring(filePath.indexOf('.') + 1);


        return Parser.parse(fileData, format);
    }
}
