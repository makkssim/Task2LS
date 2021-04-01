package ls;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileProp {
    private String name;
    private Date lastMod;
    private Integer rights;
    private Double size;

    public FileProp(File i) {
        this.name = i.getName();
        this.size = (double) i.length();
        this.rights = 0;
        if (i.canExecute()) this.rights++;
        if (i.canWrite()) this.rights += 10;
        if (i.canRead()) this.rights += 100;
        this.lastMod = new Date(i.lastModified());
    }

    public String toString(List<FileProp> files, boolean h) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        StringBuilder sb = new StringBuilder("");
        Integer ln = 0;
        for (FileProp i : files) {
            if (i.name.length() > ln) ln = i.name.length();
        }
        sb.append("Name" + " " + "Last modified       " + "Rights " + "Size\n");
        for (FileProp i : files) {
            String rig = "";
            Double siz = size;
            Integer x = 0;
            if (!h) {
                rig = rights.toString();
            } else {
                if (rights >= 100) {
                    rig += "r";
                    rights -= 100;
                } else rig += "-";
                if (rights >= 10) {
                    rig += "w";
                    rights -= 10;
                } else rig += "-";
                if (rights == 1) rig += "x";
                else rig += "-";
                while (siz >= 1024 && x<=5) {
                    siz = siz / 1024;
                    x++;
                }
            }
            List<String> s = Arrays.asList("B", "KB", "MB", "GB", "TB", "PB");
            sb.append(i.name + "    " + sdf.format(lastMod) + "    " + rig + "    " + String.format("%.2f", siz) + s.get(x));
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
