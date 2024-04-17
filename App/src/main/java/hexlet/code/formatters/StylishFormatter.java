package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for (Map<String, String> differ : paramsDiffer) {
            builder.append(getDifferLine(differ));
        }

        builder.append("}");

        return  builder.toString();
    }

    private static String getDifferLine(Map<String, String> differ) {
        StringBuilder builder = new StringBuilder();

        switch (differ.get("type")) {
            case "add" -> builder.append(getFormatLine("+", differ.get("key"), differ.get("newValue")));
            case "remove" -> builder.append(getFormatLine("-", differ.get("key"), differ.get("oldValue")));
            case "change" -> {
                builder.append(getFormatLine("-", differ.get("key"), differ.get("oldValue")));
                builder.append(getFormatLine("+", differ.get("key"), differ.get("newValue")));
            }
            default -> builder.append(getFormatLine(" ", differ.get("key"), differ.get("value")));
        }

        return  builder.toString();
    }

    private static String getFormatLine(String prefix, String key, String value) {
        return "  " + prefix + " " + key + ": " + value + "\n";
    }
}
