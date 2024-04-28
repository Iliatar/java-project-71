package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        return Files.readString(filePath).trim();
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

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTestStylish(String format) throws Exception {
        String filePath1 = getFixturePath("tree1." + format).toString();
        String filePath2 = getFixturePath("tree2." + format).toString();

        assertEquals(resultStylish, Differ.generate(filePath1, filePath2, "stylish"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTestDefault(String format) throws Exception {
        String filePath1 = getFixturePath("tree1." + format).toString();
        String filePath2 = getFixturePath("tree2." + format).toString();

        assertEquals(resultStylish, Differ.generate(filePath1, filePath2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTestPlain(String format) throws Exception {
        String filePath1 = getFixturePath("tree1." + format).toString();
        String filePath2 = getFixturePath("tree2." + format).toString();

        assertEquals(resultPlain, Differ.generate(filePath1, filePath2, "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTestJson(String format) throws Exception {
        String filePath1 = getFixturePath("tree1." + format).toString();
        String filePath2 = getFixturePath("tree2." + format).toString();

        assertEquals(resultJson, Differ.generate(filePath1, filePath2, "json"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
