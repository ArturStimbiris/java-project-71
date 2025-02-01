package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0.0",
    description = "Compares two configuration files and shows a difference.")

public class Gendiff implements Runnable {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    public static void main(String[] args) {
        CommandLine.run(new Gendiff(), args);
    }

    @Override
    public void run() {
        String diff = Differ.generate(filepath1, filepath2, format);
        System.out.println(diff);
    }
}
