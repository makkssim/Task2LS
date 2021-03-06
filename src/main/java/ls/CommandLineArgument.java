package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class CommandLineArgument {

    @Option(name = "-l", usage = "switches output to long format")
    private boolean l;

    @Option(name = "-h", usage = "switches output to human readable format", depends = {"-l"})
    private boolean h;

    @Option(name = "-r", usage = "reverses the order of output")
    private boolean r;

    @Option(name = "-o", usage = "output to file")
    private File outputName;


    @Argument(metaVar = "Name", usage = "name of File/Directory", required = true)
    private String name;

    public static void main(String[] args) {

        new CommandLineArgument().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar ls.jar -l -h -r -o outputName");
            parser.printUsage(System.err);
            return;
        }
        Code code = new Code();
        code.main(name, l, r, h, outputName);


    }


}

