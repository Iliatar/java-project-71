package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.file.NoSuchFileException;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatterName) {
        try {
            Map<String, Object> map1 = Parser.getMap(filepath1);
            Map<String, Object> map2 = Parser.getMap(filepath2);

            Map<String, Map<String, Object>> paramsDiffer = Comparer.getDifference(map1, map2);

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
