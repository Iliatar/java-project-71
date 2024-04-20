package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {
    public static String formatDiffer(Map<String, Map<String, Object>> paramsDiffer,
                                      String formatterName) throws Exception {
        String result;
        switch (formatterName) {
            case "plain":
                result = PlainFormatter.formatDiffer(paramsDiffer);
                break;
            case "json":
                result = JsonFormatter.formatDiffer(paramsDiffer);
                break;
            default:
                result = StylishFormatter.formatDiffer(paramsDiffer);
        }
        return result;
    }
}
