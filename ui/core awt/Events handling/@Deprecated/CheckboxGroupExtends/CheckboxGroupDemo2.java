import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class CheckboxGroupDemo2 extends Applet
{
    CheckboxGroup cbg;
    MyCheckbox myCheckbox1, myCheckbox2, myCheckbox3;

    @Override public void init()
    {
        cbg = new CheckboxGroup();
        myCheckbox1 = new MyCheckbox("Item 1", cbg, true);
        add(myCheckbox1);
        myCheckbox2 = new MyCheckbox("Item 2", cbg, false);
        add(myCheckbox2);
        myCheckbox3 = new MyCheckbox("Item 3", cbg, false);
        add(myCheckbox3);
    }

    class MyCheckbox extends Checkbox
    {
        public MyCheckbox(String label, CheckboxGroup cbg, boolean flag)
        {
            super(label, cbg, flag);
            enableEvents(AWTEvent.ITEM_EVENT_MASK);
        }

        @Override protected void processItemEvent(ItemEvent ie)
        {
            showStatus("Checkbox name/state: " + getLabel() + "/" + getState());
            super.processItemEvent(ie);
        }
    }
}