package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@CommandLine.Command(name = "App", mixinStandardHelpOptions = true, version = "App 0.1", description = "Unknown Application")
public class App implements Callable<Integer> {
    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to first file")
    private String filepath2;
    public static void main (String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception{
        try {
            Map<String, String> map1 = getJsonMap(filepath1);

            Map<String, String> map2 = getJsonMap(filepath2);


            String[] keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                                .distinct()
                                .sorted().toArray(String[]::new);

            StringBuilder builder = new StringBuilder();
            builder.append("{\n");
            for (String key : keys) {
                if (map1.containsKey(key) && map2.containsKey(key)
                    && map1.get(key).equals(map2.get(key))) {
                    builder.append("    " + key + ": " + map1.get(key) + "\n");
                } else {
                    if (map1.containsKey(key)) {
                        builder.append("  - " + key + ": " + map1.get(key) + "\n");
                    }
                    if (map2.containsKey(key)) {
                        builder.append("  + " + key + ": " + map2.get(key) + "\n");
                    }
                }
            }
            builder.append("}");

            System.out.println(builder);
        } catch (java.nio.file.NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }
        catch (JsonParseException e) {
            System.out.println("Error parsing json: " + e.getMessage());
        }
        return 0;
    }

    private Map<String, String> getJsonMap (String filePath) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(filePath);
        String json = Files.readString(path);
        return objectMapper.readValue(json, new TypeReference<Map<String, String>>(){});
    }
}
