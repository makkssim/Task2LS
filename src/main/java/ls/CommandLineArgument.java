package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileNotFoundException;

public class CommandLineArgument {

    @Option(name = "-l", usage = "switches output to long format")
    private boolean l = true;

    @Option(name = "-h", usage = "switches output to human readable format")
    private boolean h = true;

    @Option(name = "-r", usage = "reverses the order of output")
    private boolean r = true;

    @Option(name = "-o", usage = "output to file")
    private boolean o = true;

    @Argument(metaVar = "Name", usage = "name of File/Directory", index = 0, required = true)
    private String name;

    @Argument(usage = "output file name", index = 1)
    private String outputName;

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
        try {
            Code code = new Code();
            code.main(name, l, r, h, o, outputName);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            System.err.println("This file/directory does not exist or cannot be accessed");
        }


    }


}

