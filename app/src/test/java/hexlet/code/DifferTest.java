package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DifferTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testJsonJson() throws Exception {
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "json");
        assertEquals(resultJson, actual1);
    }

    @Test
    public void testYamlJson() throws Exception {
        String actual1 = Differ.generate("src/test/resources/yamlTree1.yml",
                "src/test/resources/yamlTree2.yml", "json");
        assertEquals(resultJson, actual1);
    }

    @Test
    public void testJsonStylish() throws Exception {
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "stylish");
        assertEquals(resultStylish, actual1);
    }

    @Test
    public void testYamlStylish() throws Exception {
        String actual2 = Differ.generate("src/test/resources/yamlTree1.yml",
                "src/test/resources/yamlTree2.yml", "stylish");
        assertEquals(resultStylish, actual2);
    }

    @Test
    public void testJsonPlain() throws Exception {
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json", "plain");
        assertEquals(resultPlain, actual1);
    }

    @Test
    public void testYamlPlain() throws Exception {
        String actual1 = Differ.generate("src/test/resources/yamlTree1.yml",
                "src/test/resources/yamlTree2.yml", "plain");
        assertEquals(resultPlain, actual1);
    }

    @Test
    public void testJsonDefault() throws Exception {
        String actual1 = Differ.generate("src/test/resources/jsonTree1.json",
                "src/test/resources/jsonTree2.json");
        assertEquals(resultStylish, actual1);
    }

    @Test
    public void testYamlDefault() throws Exception {
        String actual1 = Differ.generate("src/test/resources/yamlTree1.yml",
                "src/test/resources/yamlTree2.yml");
        assertEquals(resultStylish, actual1);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
