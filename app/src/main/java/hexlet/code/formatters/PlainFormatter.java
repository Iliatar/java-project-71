package hexlet.code.formatters;

import hexlet.code.DifferItem;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatDiffer(Map<String, DifferItem> paramsDiffer) {

        List<String> lines = paramsDiffer.keySet().stream()
                .filter(key -> !paramsDiffer.get(key).getStatusName().equals(DifferItem.UNCHANGED))
                .sorted()
                .map(key -> getDifferLine(key, paramsDiffer.get(key)))
                .toList();

        return String.join("\n", lines);
    }

    private static String getDifferLine(String key, DifferItem differItem) {
        StringBuilder builder = new StringBuilder();
        builder.append("Property '").append(key).append("' was ");

        if (differItem.getStatusName().equals(DifferItem.CHANGED)) {
            builder.append("updated. From ")
                    .append(getFormattedValue(differItem.getOldValue()))
                    .append(" to ").append(getFormattedValue(differItem.getNewValue()));
        } else if (differItem.getStatusName().equals(DifferItem.ADDED)) {
            builder.append("added with value: ")
                    .append(getFormattedValue(differItem.getNewValue()));
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
