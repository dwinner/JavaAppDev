package com.horstmann.corejava;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ��������� bean, ��������������� ��� ��������� �����������.
 * <p/>
 * @version 1.21 2001-08-15
 * @author Cay Horstmann
 */
public class ImageViewerBean extends JLabel
{
    private File file = null;
    private static final int XPREFSIZE = 200;
    private static final int YPREFSIZE = 200;
    protected static final Dimension DEFAULT_DIMENSION =
        new Dimension(XPREFSIZE, YPREFSIZE);

    public ImageViewerBean()
    {
        setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * ��������� �������� fileName.
     * <p/>
     * @param fileName ��� �����, ����������� �����������.
     */
    public void setFileName(String fileName)
    {
        try
        {
            file = new File(fileName);
            setIcon(new ImageIcon(ImageIO.read(file)));
        }
        catch (IOException ioEx)
        {
            file = null;
            setIcon(null);
        }
    }

    /**
     * ��������� �������� fileName.
     * <p/>
     * @return ��� �����, ����������� �����������.
     */
    public String getFileName()
    {
        return (file == null) ? null : file.getPath();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return DEFAULT_DIMENSION;
    }
}
