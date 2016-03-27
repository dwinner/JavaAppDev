package buggybuttontest1;

import javax.swing.JFrame;

public class BuggyButtonFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    public BuggyButtonFrame()
    {
        setTitle("BuggyButtonTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Добавление панели к фрейму.
        
        BuggyButtonPanel panel = new BuggyButtonPanel();
        add(panel);
    }
}
