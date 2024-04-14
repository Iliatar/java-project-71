package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DifferTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }
    @Test
    public void testJson() throws Exception {
        String expected1 = "{\n"
                + "  - follow: false\n"
                + "  + followed: true\n"
                + "    host: hexlet.io\n"
                + "  + login: blablator\n"
                + "    proxy: 123.234.53.22\n"
                + "  - timeout: 55\n"
                + "  + timeout: 200\n"
                + "}";
        String actual1 = Differ.generate("src/test/resources/json1.json", "src/test/resources/json2.json");
        assertEquals(expected1, actual1);

        String expected2 = "{\n"
                + "  - follow: false\n"
                + "  - host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 55\n"
                + "}";
        String actual2 = Differ.generate("src/test/resources/json1.json", "src/test/resources/json3.json");
        assertEquals(expected2, actual2);

        String expected3 = "{\n"
                +  "  + follow: false\n"
                +  "  + host: hexlet.io\n"
                +  "  + proxy: 123.234.53.22\n"
                +  "  + timeout: 55\n"
                +  "}";
        String actual3 = Differ.generate("src/test/resources/json3.json", "src/test/resources/json1.json");
        assertEquals(expected3, actual3);
    }

    @Test
    public void testObject() throws Exception {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json", "src/test/resources/jsonTree2.json");
        assertEquals(expected, actual1);
        String actual2 = Differ.generate("src/test/resources/yamlTree1.yml", "src/test/resources/yamlTree2.yml");
        assertEquals(expected, actual2);
    }

    @Test
    public void testYaml() throws Exception {
        String expected1 = "{\n"
                + "  - follow: false\n"
                + "    format: yaml\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "  + repeating: no\n"
                + "  - timeout: 55\n"
                + "  + timeout: 70\n"
                + "}";
        String actual1 = Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/yaml2.yml");
        assertEquals(expected1, actual1);

        String expected2 = "{\n"
                + "  - follow: false\n"
                + "  - format: yaml\n"
                + "  - host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 55\n"
                + "}";
        String actual2 = Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/yaml3.yml");
        assertEquals(expected2, actual2);

        String expected3 = "{\n"
                + "  + follow: false\n"
                + "  + format: yaml\n"
                + "  + host: hexlet.io\n"
                + "  + proxy: 123.234.53.22\n"
                + "  + timeout: 55\n"
                + "}";
        String actual3 = Differ.generate("src/test/resources/yaml3.yml", "src/test/resources/yaml1.yml");
        assertEquals(expected3, actual3);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
