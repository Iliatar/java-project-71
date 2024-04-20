package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonFormatter {
    public static String formatDiffer(Map<String, Map<String, Object>> paramsDiffer) throws Exception {
        return new ObjectMapper().writeValueAsString(paramsDiffer);
    }
}
