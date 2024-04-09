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
                "  - timeout: 55\n" +
                "  + timeout: 200\n" +
                "}";
        String actual1 = Differ.generate("src/test/resources/json1.json", "src/test/resources/json2.json");
        assertEquals(expected1, actual1);

        String expected2 = "{\n" +
                "  - follow: false\n" +
                "  - host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 55\n" +
                "}";
        String actual2 = Differ.generate("src/test/resources/json1.json", "src/test/resources/json3");
        assertEquals(expected2, actual2);

        String expected3 = "{\n" +
                "  + follow: false\n" +
                "  + host: hexlet.io\n" +
                "  + proxy: 123.234.53.22\n" +
                "  + timeout: 55\n" +
                "}";
        String actual3 = Differ.generate("src/test/resources/json3", "src/test/resources/json1.json");
        assertEquals(expected3, actual3);
    }
}
