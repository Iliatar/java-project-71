package hexlet.code;

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
    public Integer call() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Path path1 = Paths.get(filepath1);
        String json1 = Files.readString(path1);
        Map<String, String> map1 = objectMapper.readValue(json1, new TypeReference<Map<String, String>>(){});
        Set<String> keyset1 = map1.keySet();

        Path path2 = Paths.get(filepath2);
        String json2 = Files.readString(path2);
        Map<String, String> map2 = objectMapper.readValue(json2, new TypeReference<Map<String, String>>(){});
        Set<String> keyset2 = map2.keySet();

        String[] keys = Stream.concat(keyset1.stream(), keyset2.stream())
                                .distinct()
                                .sorted().toArray(String[]::new);

        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (String key : keys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))){
                    builder.append("    " + key + ": " + map1.get(key));
                } else {
                    builder.append("  - " + key + ": " + map1.get(key) + "\n");
                    builder.append("  + " + key + ": " + map2.get(key));
                }
            } else if (map1.containsKey(key)) {
                builder.append("  - " + key + ": " + map1.get(key));
            } else {
                builder.append("  + " + key + ": " + map2.get(key));
            }
            builder.append("\n");
        }
        builder.append("}");

        System.out.println(builder);

        return 0;
    }
}
