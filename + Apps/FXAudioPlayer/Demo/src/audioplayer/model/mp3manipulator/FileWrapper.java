package audioplayer.model.mp3manipulator;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWrapper
{
    public static final String PROP_FILE = "PROP_FILE";
    public static final String PROP_FILENAME = "PROP_FILENAME";
    public static final String PROP_LENGTH = "PROP_LENGTH";
    public static final String PROP_LASTMODIFIED = "PROP_LASTMODIFIED";
    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
       this);
    private File file;
    private String filename;
    private long length;
    private long lastModified;

    protected FileWrapper()
    {
    }

    public FileWrapper(String filename) throws IOException
    {
        this.filename = filename;
        init();
        length = file.length();
        lastModified = file.lastModified();
    }

    private void init() throws IOException
    {
        setFile(new File(getFilename()));
        if (!file.exists())
        {
            throw new FileNotFoundException("File not found " + getFilename());
        }
        if (!file.canRead())
        {
            throw new IOException("File not readable");
        }
    }

    public String getFilename()
    {
        return filename;
    }

    public long getLength()
    {
        return length;
    }

    public long getLastModified()
    {
        return lastModified;
    }

    /**
     * @return the file
     */
    public File getFile()
    {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file)
    {
        File oldFile = file;
        this.file = file;
        propertyChangeSupport.firePropertyChange(PROP_FILE, oldFile, file);
    }
}
