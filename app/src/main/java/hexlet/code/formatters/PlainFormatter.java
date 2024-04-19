package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatDiffer(Map<String, Map<String, Object>> paramsDiffer) {

        List<String> lines = paramsDiffer.keySet().stream()
                .filter(key -> !paramsDiffer.get(key).containsKey("sameValue"))
                .sorted()
                .map(key -> getDifferLine(key, paramsDiffer.get(key)))
                .toList();

        return String.join("\n", lines);
    }

    private static String getDifferLine(String key, Map<String, Object> differ) {
        StringBuilder builder = new StringBuilder();
        builder.append("Property '").append(key).append("' was ");

        if (differ.containsKey("newValue") && differ.containsKey("oldValue")) {
            builder.append("updated. From ")
                    .append(getFormattedValue(differ.get("oldValue")))
                    .append(" to ").append(getFormattedValue(differ.get("newValue")));
        } else if (differ.containsKey("newValue")) {
            builder.append("added with value: ")
                    .append(getFormattedValue(differ.get("newValue")));
        } else {
            builder.append("removed");
        }

        return builder.toString();
    }

    private static String getFormattedValue(Object objectValue) {
        if (objectValue == null) {
            return "null";
        } else if (objectValue instanceof Boolean || objectValue instanceof Number) {
            return objectValue.toString();
        } else if (objectValue instanceof String) {
            return "'" + objectValue + "'";
        } else {
            return "[complex value]";
        }
    }
}
