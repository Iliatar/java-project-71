package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    public void test () throws Exception {
        String expected = "{\n" +
                "  - follow: false\n" +
                "  + followed: true\n" +
                "    host: hexlet.io\n" +
                "  + login: blablator\n" +
                "    proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 200\n" +
                "}";
        String actual = Differ.generate("json1.json", "json2.json");
        assertEquals(expected, actual);
    }

}
