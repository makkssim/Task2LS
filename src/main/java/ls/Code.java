package ls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Code {
    public static void main(String name, boolean l, boolean r, boolean h, boolean o, String output) {
        File dir = new File(name);
        ArrayList<FileProp> files = new ArrayList<>();
        if (dir.isFile()) {
            files.add(new FileProp(dir));
            l = true;
        } else if (l) for (File i : dir.listFiles()) {
            files.add(new FileProp(i));
        }
        if (!r) Collections.sort(files, Comparator.comparing(FileProp::getName));
        else
            Collections.sort(files, Comparator.comparing(FileProp::getName).reversed());
        String finalOutput;
        if (!l) {
            StringBuilder sb = new StringBuilder("");
            File[] f = dir.listFiles();
            if (r) Collections.reverse(Arrays.asList(f));
            for (File i : f) sb.append(i.getName() + System.lineSeparator());
            finalOutput = sb.toString();
        } else {
            FileProp a = new FileProp(dir);
            finalOutput = a.toString(files, h);
        }
        if (!o) System.out.println(finalOutput);
        else {
            File outputfile = new File(output);
            try {
                FileWriter writer = new FileWriter(outputfile);
                writer.write(finalOutput);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
