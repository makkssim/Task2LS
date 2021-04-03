
import ls.CommandLineArgument;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class lsTest {


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
        assertEquals("Name                         Last modified       Rights    Size" + System.lineSeparator() +
                "$RECYCLE.BIN                 12.08.2020 23:00    rwx       1,99KB" + System.lineSeparator() +
                "Games                        30.03.2021 11:27    rwx       32,18GB" + System.lineSeparator() +
                "KotlinEduProjectsSPBSTU      07.09.2020 21:30    rwx       3,31MB" + System.lineSeparator() +
                "RPCS3                        20.11.2020 17:57    rwx       29,52GB" + System.lineSeparator() +
                "SteamLibrary                 21.11.2020 14:47    rwx       477,23KB" + System.lineSeparator() +
                "System Volume Information    01.01.1970 03:00    rwx       0,00B" + System.lineSeparator() +
                "Tests                        03.04.2021 21:40    rwx       1,45KB" + System.lineSeparator() +
                "WO Mic                       11.09.2020 10:22    rwx       3,52MB" + System.lineSeparator() +
                "Xenia(Xbox360)               20.11.2020 19:37    rwx       7,56GB" + System.lineSeparator() +
                "msdownld.tmp                 20.11.2020 15:46    rwx       0,00B" + System.lineSeparator() +
                "Новая папка                  15.03.2021 21:52    rwx       714,67MB", main(new String[]{"D:\\", "-l", "-h"}).trim());

        assertEquals("Name                         Last modified       Rights    Size" + System.lineSeparator() +
                "$RECYCLE.BIN                 12.08.2020 23:00    111       2035,00B" + System.lineSeparator() +
                "Games                        30.03.2021 11:27    111       34554978962,00B" + System.lineSeparator() +
                "KotlinEduProjectsSPBSTU      07.09.2020 21:30    111       3468362,00B" + System.lineSeparator() +
                "RPCS3                        20.11.2020 17:57    111       31698878794,00B" + System.lineSeparator() +
                "SteamLibrary                 21.11.2020 14:47    111       488680,00B" + System.lineSeparator() +
                "System Volume Information    01.01.1970 03:00    111       0,00B" + System.lineSeparator() +
                "Tests                        03.04.2021 21:40    111       1488,00B" + System.lineSeparator() +
                "WO Mic                       11.09.2020 10:22    111       3690221,00B" + System.lineSeparator() +
                "Xenia(Xbox360)               20.11.2020 19:37    111       8118307541,00B" + System.lineSeparator() +
                "msdownld.tmp                 20.11.2020 15:46    111       0,00B" + System.lineSeparator() +
                "Новая папка                  15.03.2021 21:52    111       749383862,00B", main(new String[]{"D:\\", "-l"}).trim());

        assertEquals("Новая папка" + System.lineSeparator() +
                "Xenia(Xbox360)" + System.lineSeparator() +
                "WO Mic" + System.lineSeparator() +
                "Tests" + System.lineSeparator() +
                "System Volume Information" + System.lineSeparator() +
                "SteamLibrary" + System.lineSeparator() +
                "RPCS3" + System.lineSeparator() +
                "msdownld.tmp" + System.lineSeparator() +
                "KotlinEduProjectsSPBSTU" + System.lineSeparator() +
                "Games" + System.lineSeparator() +
                "$RECYCLE.BIN", main(new String[]{"D:\\", "-r"}).trim());

        URL url = CommandLineArgument.class.getResource("/res1");
        File file = new File(url.toURI());
        CommandLineArgument.main(new String[]{"D:\\", "-l", "-h", "-o", "D:\\Tests\\res1"});
        assertTrue(FileUtils.contentEquals(file, new File("D:\\Tests\\res1")));


    }


}
