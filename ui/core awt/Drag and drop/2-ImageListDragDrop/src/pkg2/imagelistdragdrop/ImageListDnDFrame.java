package pkg2.imagelistdragdrop;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ImageListDnDFrame extends JFrame
{
    private ImageList list1;
    private ImageList list2;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;

    public ImageListDnDFrame() throws HeadlessException
    {
        setTitle("ImageListDnDTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        list1 = new ImageList(new File("images1").listFiles());
        list2 = new ImageList(new File("images2").listFiles());
        setLayout(new GridLayout(2, 1));
        add(new JScrollPane(list1));
        add(new JScrollPane(list2));
    }
}
