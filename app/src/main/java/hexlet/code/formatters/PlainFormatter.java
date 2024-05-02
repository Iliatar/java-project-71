package hexlet.code.formatters;

import hexlet.code.DifferItem;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatDiffer(Map<String, DifferItem> paramsDiffer) {

        List<String> lines = paramsDiffer.keySet().stream()
                .filter(key -> !paramsDiffer.get(key).getStatusName().equals(DifferItem.UNCHANGED))
                .map(key -> getDifferLine(key, paramsDiffer.get(key)))
                .toList();

        return String.join("\n", lines);
    }

    private static String getDifferLine(String key, DifferItem differItem) {
        StringBuilder builder = new StringBuilder();
        builder.append("Property '").append(key).append("' was ");

        switch (differItem.getStatusName()) {
            case DifferItem.CHANGED -> builder.append("updated. From ")
                    .append(getFormattedValue(differItem.getOldValue()))
                    .append(" to ").append(getFormattedValue(differItem.getNewValue()));
            case DifferItem.ADDED -> builder.append("added with value: ")
                    .append(getFormattedValue(differItem.getNewValue()));
            case DifferItem.DELETED -> builder.append("removed");
            default -> throw new RuntimeException("Unknown key: " + key);
        }

        return builder.toString();
    }

    private static String getFormattedValue(Object objectValue) {
        if (objectValue == null) {
            return "null";
        } else if (objectValue instanceof Map || objectValue instanceof List) {
            return "[complex value]";
        } else if (objectValue instanceof String) {
            return "'" + objectValue + "'";
        } else {
            return objectValue.toString();
        }
    }
}
