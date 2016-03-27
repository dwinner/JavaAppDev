// Класс File.
import java.io.File;

public class FileDemo
{
    static void p(String s)
    {
        System.out.println(s);
    }

    public static void main(String args[])
    {
        File f1 = new File("COPYRIGHT");
        p("File name: " + f1.getName());
        p("Path: " + f1.getPath());
        p("Absolute path: " + f1.getAbsolutePath());
        p("Parent directory: " + f1.getParent());
        p(f1.exists() ? "exists" : "not exists");
        p(f1.canWrite() ? "for writing" : "not for writing");
        p(f1.canRead() ? "for reading" : "not for reading");
        p("is " + (f1.isDirectory() ? "" : "not a directory"));
        p(f1.isFile() ? "a normal file" : "may be named channel");
        p(f1.isAbsolute() ? "absolute" : "not absolute");
        p("last time modification: " + f1.lastModified());
        p("File size: " + f1.length() + " bytes");
    }
}
