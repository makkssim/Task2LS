package ls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Code {
    public static void main(String name, boolean l, boolean r, boolean h, boolean o, String output) {
        File dir = new File(name);
        ArrayList<FileProp> files = new ArrayList<FileProp>();
        if (dir.isFile()){
            files.add(FileProp.fileToFileProp(dir));
            l = true;
        }
        else if(l) for(File i:dir.listFiles()){
            files.add(FileProp.fileToFileProp(i));
        }
        if(!r) Collections.sort(files, Comparator.comparing(FileProp::getName)); else
            Collections.sort(files, Comparator.comparing(FileProp::getName).reversed());
        String finalOutput;
        if(!l){
            StringBuilder sb = new StringBuilder("");
            for(File i: dir.listFiles()) sb.append(i.getName()+"\n");
            finalOutput = sb.toString();
        } else finalOutput = FileProp.toString(files,h);
        if(!o) System.out.println(finalOutput); else {
            File outputfile = new File(output);
            try{
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