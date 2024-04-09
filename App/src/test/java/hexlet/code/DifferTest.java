package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    public void test () throws Exception {
        String expected1 = "{\n" +
                "  - follow: false\n" +
                "  + followed: true\n" +
                "    host: hexlet.io\n" +
                "  + login: blablator\n" +
                "    proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 200\n" +
                "}";
        String actual1 = Differ.generate("json1.json", "json2.json");
        assertEquals(expected1, actual1);

        String expected2 = "{\n" +
                "  - follow: false\n" +
                "  - host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "}";
        String actual2 = Differ.generate("json1.json", "json3");
        assertEquals(expected2, actual2);

        String expected3 = "{\n" +
                "  + follow: false\n" +
                "  + host: hexlet.io\n" +
                "  + proxy: 123.234.53.22\n" +
                "  + timeout: 50\n" +
                "}";
        String actual3 = Differ.generate("json3", "json1.json");
        assertEquals(expected3, actual3);
    }
}
