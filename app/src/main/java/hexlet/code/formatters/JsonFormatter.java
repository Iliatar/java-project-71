package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String formatDiffer(List<Map<String, String>> paramsDiffer) {
        String json = "";

        paramsDiffer = paramsDiffer.stream()
                .filter(map -> !map.get("action").equals("equal"))
                .toList();

        paramsDiffer.stream().forEach(map -> map.remove("action"));

        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(paramsDiffer);
        } catch (Exception e) {

        }

        return json;
    }
}
