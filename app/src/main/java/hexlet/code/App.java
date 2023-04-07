package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff",
         version = "gendiff 1.0",
         mixinStandardHelpOptions = true,
         description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

}
