import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class ButtonDemo2 extends Applet
{
    MyButton myButton;
    static int i = 0;

    @Override public void init()
    {
        myButton = new MyButton("Test Button");
        add(myButton);
    }

    class MyButton extends Button
    {
        public MyButton(String label)
        {
            super(label);
            enableEvents(AWTEvent.ACTION_EVENT_MASK);
        }

        @Override protected void processActionEvent(ActionEvent ae)
        {
            showStatus("action event: " + i++);
            super.processActionEvent(ae);
        }
    }
}