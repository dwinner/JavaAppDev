/*
 * Если для выполнения апплета используется appletviewer, то она
 * считывает указанные ниже дескрипторы. В этом случае отдельный
 * HTML-файл не нужен.
 * <applet code="AppletApplication.class" width="200" height="200"></applet>
 */

import java.awt.EventQueue;
import javax.swing.*;

/**
 * Это апплет. И это приложение. Два в одном!
 * @version 1.32 2007-04-28
 * @author Cay Horstmann
 */
public class AppletApplication extends NotHelloWorldApplet
{
    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 200;
   
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                AppletFrame frame = new AppletFrame(new NotHelloWorldApplet());
                frame.setTitle("NotHelloWorldApplet");
                frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}