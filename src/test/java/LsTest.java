
import ls.CommandLineArgument;
import ls.FileProp;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LsTest {


    private String main(String[] args) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        System.setOut(new PrintStream(baos));
        System.setErr(new PrintStream(baos));
        CommandLineArgument.main(args);
        System.out.flush();
        System.err.flush();
        System.setOut(oldOut);
        System.setErr(oldErr);
        return (baos.toString());
    }


    @Test
    public void ls() throws  IOException {
        ArrayList<FileProp> files = new ArrayList<>();
        File resource = new File(".\\target\\test-classes");
        for (int i = 0; i < 10; i++) {
            File.createTempFile("temp", ".tmp",resource);
        }
        File r1 = new File(resource, "r1");
        File r2 = new File(resource, "r2");
        StringBuilder res = new StringBuilder();

        for (File i : resource.listFiles()) {
            files.add(new FileProp(i));
            res.append(i.getName()).append(System.lineSeparator());
        }
        assertEquals((FileProp.textTable(files, true)).trim(), main(new String[]{resource.getAbsolutePath(), "-l", "-h"}).trim());
        assertEquals(res.toString().trim(), main(new String[]{resource.getAbsolutePath()}).trim());
        CommandLineArgument.main(new String[]{resource.getAbsolutePath(), "-l", "-h", "-o", r2.getAbsolutePath()});
        try (FileWriter writer = new FileWriter(r1)) {
            writer.write(FileProp.textTable(files, true));
        }
        assertTrue(FileUtils.contentEquals(r1, r2));


    }


}
