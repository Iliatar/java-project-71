package hexlet.code;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for (Map<String, String> differ : paramsDiffer) {
            builder.append(getFormatLine(differ));
        }

        builder.append("}");

        return  builder.toString();
    }
    private static String getFormatLine(Map<String, String> differ) {
        StringBuilder builder = new StringBuilder();

        builder.append("  ");

        switch (differ.get("type")) {
            case "add" -> builder.append("+ ");
            case "remove" -> builder.append("- ");
            default -> builder.append("  ");
        }

        builder.append(differ.get("key")).append(": ").append(differ.get("value")).append("\n");

        return  builder.toString();
    }
}
