package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer, String formatterName) {
        String result;
        switch (formatterName) {
            case "stylish":
                result = StylishFormatter.formatDiffer(paramsDiffer);
                break;
            case "plain":
                result = PlainFormatter.formatDiffer(paramsDiffer);
                break;
            case "json":
                result = JsonFormatter.formatDiffer(paramsDiffer);
                break;
            default:
                result = "";
        }
        return result;
    }
}
