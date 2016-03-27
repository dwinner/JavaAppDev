package persistentframetest;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Данная программа демонстрирует использование XMLEncoder и XMLDecoder для сохранения и
 * восстановления фрейма.
 * <p/>
 * @version 1.01 2007-10-03
 * @author Cay Horstmann
 */
public class PersistentFrameTest
{
    public static void main(String[] args)
    {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        PersistentFrameTest test = new PersistentFrameTest();
        test.init();
    }
    
    private static JFileChooser chooser;
    private JFrame frame;
    
    public void init()
    {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PersistentFrameTest");
        frame.setSize(400, 200);
        
        JButton loadButton = new JButton("Load");
        frame.add(loadButton);
        loadButton.addActionListener(EventHandler.create(ActionListener.class, this, "load"));
        
        JButton saveButton = new JButton("Save");
        frame.add(saveButton);
        saveButton.addActionListener(EventHandler.create(ActionListener.class, this, "save"));
        
        frame.setVisible(true);
    }
    
    public void load()
    {
        // Отображение диалогового окна выбора файлов.
        int r = chooser.showOpenDialog(null);
        
        // Если файл выбран, открыть его.
        if (r == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = chooser.getSelectedFile();
                try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(file)))
                {
                    JFrame newFrame = (JFrame) decoder.readObject();
                    // decoder.readObject();
                }
            }
            catch (IOException ioEx)
            {
                JOptionPane.showMessageDialog(null, ioEx);
            }
        }
    }
    
    public void save()
    {
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = chooser.getSelectedFile();
                try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file)))
                {
                    encoder.writeObject(frame);
                }
            }
            catch (IOException ioEx)
            {
                JOptionPane.showMessageDialog(null, ioEx);
            }
        }
    }
}
