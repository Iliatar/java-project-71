package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "", mixinStandardHelpOptions = true,
        version = "App 0.1", description = "Unknown Application")
public final class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to first file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }


}
