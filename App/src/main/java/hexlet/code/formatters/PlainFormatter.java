package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer) {
        String[] lines = paramsDiffer.stream()
                .filter(map -> !map.get("action").equals("equal"))
                .map(PlainFormatter::getDifferLine)
                .toArray(String[]::new);

        return String.join("\n", lines);
    }

    private static String getDifferLine(Map<String, String> differ) {
        StringBuilder builder = new StringBuilder();
        builder.append("Property '").append(differ.get("key")).append("' was ");

        switch (differ.get("action")) {
            case "add" -> builder.append("added with value: ")
                    .append(getFormattedValue(differ.get("newValue"), differ.get("newType")));
            case "remove" -> builder.append("removed");
            case "change" -> builder.append("updated. From ")
                    .append(getFormattedValue(differ.get("oldValue"), differ.get("oldType")))
                    .append(" to ").append(getFormattedValue(differ.get("newValue"), differ.get("newType")));
            default -> builder.append("not changed");
        }

        return builder.toString();
    }

    private static String getFormattedValue(String rawValue, String type) {
        if (type.equals("Object") && !rawValue.equals("null")) {
            return "[complex value]";
        } else if (type.equals("String")) {
            return "'" + rawValue + "'";
        } else {
            return  rawValue;
        }
    }
}
