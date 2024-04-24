package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferItem;

import java.util.Map;

public class JsonFormatter {
    public static String formatDiffer(Map<String, DifferItem> paramsDiffer) throws Exception {
        return new ObjectMapper().writeValueAsString(paramsDiffer);
    }
}
