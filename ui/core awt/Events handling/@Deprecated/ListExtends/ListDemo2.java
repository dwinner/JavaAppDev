import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class ListDemo2 extends Applet
{
    MyList list;

    @Override public void init()
    {
        list = new MyList();
        list.add("Red");
        list.add("Green");
        list.add("Blue");
        add(list);
    }

    class MyList extends List
    {
        public MyList()
        {
            enableEvents(AWTEvent.ITEM_EVENT_MASK | AWTEvent.ACTION_EVENT_MASK);
        }

        @Override protected void processActionEvent(ActionEvent ae)
        {
            showStatus("Action event: " + ae.getActionCommand());
            super.processActionEvent(ae);
        }

        @Override protected void processItemEvent(ItemEvent ie)
        {
            showStatus("Item event: " + getSelectedItem());
            super.processItemEvent(ie);
        }
    }
}