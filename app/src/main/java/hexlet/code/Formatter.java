package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {
    public static String formatDiffer(Map<String, DifferItem> paramsDiffer,
                                      String formatterName) throws Exception {
        String result;
        switch (formatterName) {
            case "plain":
                result = PlainFormatter.formatDiffer(paramsDiffer);
                break;
            case "json":
                result = JsonFormatter.formatDiffer(paramsDiffer);
                break;
            case "stylish":
                result = StylishFormatter.formatDiffer(paramsDiffer);
                break;
            default:
                throw new RuntimeException("Unknown formatter: " + formatterName);
        }
        return result;
    }
}
