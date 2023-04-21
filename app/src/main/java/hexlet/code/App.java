package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Option(names = {"-t", "--type"},
            paramLabel = "file type",
            description = "file format for comparing files json/yaml [default:json]",
            defaultValue = "json")
    private String fileType;
    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;
    @Override
    public Integer call() throws Exception {
        ObjectMapper mapper = fileType.equals("yaml") ? new YAMLMapper() : new ObjectMapper();
        Formatter formatter;
        switch (format) {
            case "stylish":
                formatter = new StylishFormatter();
                break;
            case "plain":
                formatter = new PlainFormatter();
                break;
            case "json":
                formatter = new JsonFormatter();
                break;
            default:
                formatter = new StylishFormatter();
        }
        Differ.setMapper(mapper);
        String diff = Differ.generate(new File(filepath1), new File(filepath2), formatter);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
