package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer) {
        String[] lines = paramsDiffer.stream()
                .filter(map -> !map.get("type").equals("equal"))
                .map(PlainFormatter::getDifferLine)
                .toArray(String[]::new);

        return String.join("\n", lines);
    }

    private static String getDifferLine(Map<String, String> differ) {
        StringBuilder builder = new StringBuilder();
        builder.append("Property '").append(differ.get("key")).append("' was ");

        switch (differ.get("type")) {
            case "add" -> builder.append("added with value: ").append(getFormattedValue(differ.get("newValue")));
            case "remove" -> builder.append("removed");
            case "change" -> builder.append("updated. From ").append(getFormattedValue(differ.get("oldValue")))
                    .append(" to ").append(getFormattedValue(differ.get("newValue")));
            default -> builder.append("not changed");
        }

        return builder.toString();
    }

    private static String getFormattedValue(String rawValue) {
        if (rawValue.equals("null") || rawValue.equals("true") || rawValue.equals("false")) {
            return rawValue;
        } else if (rawValue.matches("-?\\d+(\\.\\d+)?")) {
            return  rawValue;
        } else if (rawValue.startsWith("[") || rawValue.startsWith("{")) {
            return "[complex value]";
        } else {
            return "'" + rawValue + "'";
        }
    }
}
