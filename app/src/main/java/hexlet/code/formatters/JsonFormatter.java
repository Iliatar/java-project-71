package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String formatDiffer(Map<String, Map<String, Object>> paramsDiffer) throws Exception {
        String json = "";

        List<String> removedKeys = paramsDiffer.keySet().stream()
                .filter(key -> paramsDiffer.get(key).containsKey("sameValue")).toList();
        removedKeys.stream().forEach(paramsDiffer::remove);

        json = new ObjectMapper().writeValueAsString(paramsDiffer);

        return json;
    }
}
