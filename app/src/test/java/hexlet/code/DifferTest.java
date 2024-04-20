package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public final class DifferTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }
    @Test
    public void testJson() throws Exception {
        String expected1 = "{\"setting2\":{\"newValue\":300,\"oldValue\":200},\"key1\":{\"oldValue\":\"value1\"}"
                + ",\"setting3\":{\"newValue\":\"none\",\"oldValue\":true},\"key2\":{\"newValue\":\"value2\"}"
                + ",\"chars2\":{\"newValue\":false,\"oldValue\":[\"d\",\"e\",\"f\"]},"
                + "\"setting1\":{\"newValue\":\"Another value\""
                + ",\"oldValue\":\"Some value\"},\"chars1\":{\"sameValue\":[\"a\",\"b\",\"c\"]},"
                + "\"numbers4\":{\"newValue\":[4,5,6]}"
                + ",\"numbers3\":{\"oldValue\":[3,4,5]},\"numbers2\":{\"newValue\":[22,33,44,55],"
                + "\"oldValue\":[2,3,4,5]}"
                + ",\"numbers1\":{\"sameValue\":[1,2,3,4]},"
                + "\"obj1\":{\"newValue\":{\"nestedKey\":\"value\",\"isNested\":true}}"
                + ",\"default\":{\"newValue\":[\"value1\",\"value2\"],\"oldValue\":null}"
                + ",\"checked\":{\"newValue\":true,\"oldValue\":false},\"id\":{\"newValue\":null,\"oldValue\":45}}";
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "json");
        assertEquals(expected1, actual1);
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
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "stylish");
        assertEquals(expected, actual1);
        String actual2 = Differ.generate("src/test/resources/yamlTree1.yml",
                "src/test/resources/yamlTree2.yml", "stylish");
        assertEquals(expected, actual2);
    }

    @Test
    public void testPlainFormatter() throws Exception {
        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "plain");
        assertEquals(expected, actual1);
    }

    /*@Test
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
        String actual1 = Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/yaml2.yml", "stylish");
        assertEquals(expected1, actual1);

        String expected2 = "{\n"
                + "  - follow: false\n"
                + "  - format: yaml\n"
                + "  - host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 55\n"
                + "}";
        String actual2 = Differ.generate("src/test/resources/yaml1.yml", "src/test/resources/yaml3.yml", "stylish");
        assertEquals(expected2, actual2);

        String expected3 = "{\n"
                + "  + follow: false\n"
                + "  + format: yaml\n"
                + "  + host: hexlet.io\n"
                + "  + proxy: 123.234.53.22\n"
                + "  + timeout: 55\n"
                + "}";
        String actual3 = Differ.generate("src/test/resources/yaml3.yml", "src/test/resources/yaml1.yml", "stylish");
        assertEquals(expected3, actual3);
    }*/

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
