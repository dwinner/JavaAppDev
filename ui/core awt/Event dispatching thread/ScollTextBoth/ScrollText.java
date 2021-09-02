
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ScrollText extends JApplet
{
    private JLabel jlab;
    private String msg = " Swing makes the GUI move! ";
    private ActionListener scroller;
    private Timer stTimer; // this timer controls the scroll rate
    private int counter; // use to reverse the scroll.
    // This value controls when the scoll direction changes. 
    private int scrollLimit;

    // Initialize the applet. 
    @Override
    public void init()
    {
        counter = 0;
        scrollLimit = 100;

        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    guiInit();
                }

            });
        }
        catch (InterruptedException | InvocationTargetException exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }

    // Start the timer when the applet is started.
    @Override
    public void start()
    {
        stTimer.start();
    }

    // Stop the timer when the applet is stopped.
    @Override
    public void stop()
    {
        stTimer.stop();
    }

    // Stop the timer when the applet is destroyed.
    @Override
    public void destroy()
    {
        stTimer.stop();
    }

    // Initialize the timer GUI.
    private void guiInit()
    {
        // Create the label that will scroll the message. 
        jlab = new JLabel(msg);
        jlab.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the action listener for the timer.
        // This version reverse the direction of scroll
        // every 20 seconds.
        scroller = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (counter < scrollLimit)
                {
                    // Left-scroll the message one character.
                    char ch = msg.charAt(0);
                    msg = msg.substring(1, msg.length());
                    msg += ch;
                }
                else
                {
                    // Right-scroll the message one character.
                    char ch = msg.charAt(msg.length() - 1);
                    msg = msg.substring(0, msg.length() - 1);
                    msg = ch + msg;
                    if (counter == scrollLimit * 2)
                    {
                        counter = 0;
                    }
                }
                counter ++;
                jlab.setText(msg);
            }

        };

        // Create the timer.
        stTimer = new Timer(200, scroller);

        // Add the label to the applet content pane.
        getContentPane().add(jlab);
    }

}