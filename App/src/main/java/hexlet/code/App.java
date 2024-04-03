package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "App", mixinStandardHelpOptions = true, version = "App 0.1", description = "Unknown Application")
public class App implements Callable<Integer> {
    public static void main (String[] args) {
        int exitCode = new picocli.CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello, project-71!");
        return 1;
    }
}
