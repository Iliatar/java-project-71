package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String formatDiffer(Map<String, Map<String, Object>> paramsDiffer) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        List<String> sortedKeys = paramsDiffer.keySet().stream()
                .sorted()
                .toList();

        for (String key : sortedKeys) {
            builder.append(getDifferLine(key, paramsDiffer.get(key)));
        }

        builder.append("}");

        return  builder.toString();
    }

    private static String getDifferLine(String key, Map<String, Object> differ) {
        StringBuilder builder = new StringBuilder();

        if (differ.containsKey("sameValue")) {
            builder.append(getFormatLine(" ", key, differ.get("sameValue")));
        } else if (differ.containsKey("newValue") && differ.containsKey("oldValue")) {
            builder.append(getFormatLine("-", key, differ.get("oldValue")));
            builder.append(getFormatLine("+", key, differ.get("newValue")));
        } else if (differ.containsKey("newValue")) {
            builder.append(getFormatLine("+", key, differ.get("newValue")));
        } else {
            builder.append(getFormatLine("-", key, differ.get("oldValue")));
        }

        return  builder.toString();
    }

    private static String getFormatLine(String prefix, String key, Object value) {
        return "  " + prefix + " " + key + ": " + String.valueOf(value) + "\n";
    }
}
