import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class CheckboxDemo2 extends Applet
{
    MyCheckbox myCheckbox1, myCheckbox2, myCheckbox3;

    @Override public void init()
    {
        myCheckbox1 = new MyCheckbox("Item 1");
        add(myCheckbox1);
        myCheckbox2 = new MyCheckbox("Item 2");
        add(myCheckbox2);
        myCheckbox3 = new MyCheckbox("Item 3");
        add(myCheckbox3);
    }

    class MyCheckbox extends Checkbox
    {
        public MyCheckbox(String label)
        {
            super(label);
            enableEvents(AWTEvent.ITEM_EVENT_MASK);
        }

        @Override protected void processItemEvent(ItemEvent ie)
        {
            showStatus("Checkbox name/state: " + getLabel() + "/" + getState());
            super.processItemEvent(ie);
        }
    }
}