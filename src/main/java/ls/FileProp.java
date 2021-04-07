package ls;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


public class FileProp {
    private String name;
    private Date lastMod;
    private Map rights = new HashMap();


    private Double size;

    public FileProp(File i) {
        this.name = i.getName();
        if (i.isFile()) this.size = (double) i.length();
        else this.size = (double) FileUtils.sizeOfDirectory(i);
        boolean j = i.canExecute();
        this.rights.put("execute", i.canExecute());
        this.rights.put("write", i.canWrite());
        this.rights.put("read", i.canRead());
        this.lastMod = new Date(i.lastModified());
    }

    public static String textTable(List<FileProp> files, boolean h) {
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
                if ((boolean) i.rights.get("execute"))
                    rig += "1";
                else rig += "0";
                if ((boolean) i.rights.get("write"))
                    rig += "1";
                else rig += "0";
                if ((boolean) i.rights.get("read")) rig += "1";
                else rig += "0";
            } else {
                if ((boolean) i.rights.get("execute"))
                    rig += "r";
                else rig += "-";
                if ((boolean) i.rights.get("write"))
                    rig += "w";
                else rig += "-";
                if ((boolean) i.rights.get("read")) rig += "x";
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
