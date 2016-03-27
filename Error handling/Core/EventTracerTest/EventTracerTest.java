
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.swing.*;

/**
 * Трассировка событий AWT
 * @version 1.13 2007-06-12
 * @author Cay Horstmann
 */
public class EventTracerTest
{
    private static final String DEFAULT_TRACER_FILE = "AWTTraceLog.txt";
    
    /**
     * Перенаправление потока в файл.
     * @param aFile Файл, в который будет поток System.out
     */
    public static void setAWTTracerFile(String aFile)
    {
        File theFile = new File(aFile);
        if (!theFile.exists())
        {
            throw new RuntimeException(new FileNotFoundException(aFile.toString()));
        }
        
        PrintStream consoleStream;
        try
        {
            consoleStream = new PrintStream(theFile);
            System.setOut(consoleStream);
            System.setErr(consoleStream);
        }
        catch (FileNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                setAWTTracerFile(DEFAULT_TRACER_FILE);
                JFrame frame = new EventTracerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class EventTracerFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;

    EventTracerFrame()
    {
        setTitle("EventTracerTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление линейного регулятора и кнопки
        add(new JSlider(), BorderLayout.NORTH);
        add(new JButton("Test"), BorderLayout.SOUTH);

        // Перехват всех событий, связанных с компонентами в составе фрейма.
        EventTracer tracer = new EventTracer();
        tracer.add(this);
    }
}
