package ls;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


public class FileProp {
    private String name;
    private Date lastMod;
    private List<Boolean> rights = new ArrayList();
    private Double size;

    public FileProp(File i) {
        this.name = i.getName();
        if (i.isFile()) this.size = (double) i.length();
        else this.size = (double) FileUtils.sizeOfDirectory(i);
        boolean j = i.canExecute();
        this.rights.add(true);
        this.rights.add(0, i.canExecute());
        this.rights.add(1, i.canWrite());
        this.rights.add(2, i.canRead());
        this.lastMod = new Date(i.lastModified());
    }

    public String toString(List<FileProp> files, boolean h) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        StringBuilder sb = new StringBuilder("");
        Integer ln = 0;
        for (FileProp i : files) {
            if (i.name.length() > ln) ln = i.name.length();
        }
        sb.append("Name" + " ".repeat(ln) + "Last modified       " + "Rights    " + "Size" + System.lineSeparator());
        for (FileProp i : files) {
            String rig = "";
            Double siz = i.size;
            Integer x = 0;
            if (!h) {
                if (i.rights.get(0))
                    rig += "1";
                else rig += "0";
                if (i.rights.get(1))
                    rig += "1";
                else rig += "0";
                if (i.rights.get(2)) rig += "1";
                else rig += "0";
            } else {
                if (i.rights.get(0))
                    rig += "r";
                else rig += "-";
                if (i.rights.get(1))
                    rig += "w";
                else rig += "-";
                if (i.rights.get(2)) rig += "x";
                else rig += "-";
                while (siz >= 1024 && x <= 5) {
                    siz = siz / 1024;
                    x++;
                }
            }
            List<String> s = Arrays.asList("B", "KB", "MB", "GB", "TB", "PB");
            sb.append(i.name + " ".repeat(ln - i.name.length() + 4) + sdf.format(i.lastMod) + "    " + rig + "       " + String.format("%.2f", siz) + s.get(x) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }
}
