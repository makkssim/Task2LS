
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
    public void ls() throws URISyntaxException, IOException {
        ArrayList<FileProp> files = new ArrayList<>();
        File resource = new File(CommandLineArgument.class.getResource("/res1").toURI());
        for (int i = 0; i < 1; i++) {
            File.createTempFile("temp", ".tmp", new File(resource.getParent()));
        }
        File r1 = new File(resource.getParent(), "r1");
        File r2 = new File(resource.getParent(), "r2");
        String res = "";
        File g = new File(resource.getParent());
        for (File i : g.listFiles()) {
            files.add(new FileProp(i));
            res += i.getName() + System.lineSeparator();
        }
        assertEquals((FileProp.textTable(files, true)).trim(), main(new String[]{resource.getParent(), "-l", "-h"}).trim());
        assertEquals(res.trim(), main(new String[]{resource.getParent()}).trim());
        CommandLineArgument.main(new String[]{resource.getParent(), "-l", "-h", "-o", r2.getAbsolutePath()});
        try (FileWriter writer = new FileWriter(r1);) {
            writer.write(FileProp.textTable(files, true));
        }
        assertTrue(FileUtils.contentEquals(r1, r2));


    }


}
