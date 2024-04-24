package hexlet.code.formatters;

import hexlet.code.DifferItem;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String formatDiffer(Map<String, DifferItem> paramsDiffer) {
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

    private static String getDifferLine(String key, DifferItem differItem) {
        StringBuilder builder = new StringBuilder();

        switch (differItem.getStatusName()) {
            case DifferItem.CHANGED -> {
                builder.append(getFormatLine("-", key, differItem.getOldValue()));
                builder.append(getFormatLine("+", key, differItem.getNewValue()));
            }
            case DifferItem.ADDED -> builder.append(getFormatLine("+", key, differItem.getNewValue()));
            case DifferItem.DELETED -> builder.append(getFormatLine("-", key, differItem.getOldValue()));
            case DifferItem.UNCHANGED -> builder.append(getFormatLine(" ", key, differItem.getNewValue()));
            default -> throw new RuntimeException("Unknown key: " + key);
        }

        return  builder.toString();
    }

    private static String getFormatLine(String prefix, String key, Object value) {
        return "  " + prefix + " " + key + ": " + String.valueOf(value) + "\n";
    }
}
