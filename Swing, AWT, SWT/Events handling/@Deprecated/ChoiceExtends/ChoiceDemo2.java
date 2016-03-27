import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class ChoiceDemo2 extends Applet
{
    MyChoice choice;

    @Override public void init()
    {
        choice = new MyChoice();
        choice.add("Red");
        choice.add("Green");
        choice.add("Blue");
        add(choice);
    }

    class MyChoice extends Choice
    {
        public MyChoice()
        {
            enableEvents(AWTEvent.ITEM_EVENT_MASK);
        }

        @Override protected void processItemEvent(ItemEvent ie)
        {
            showStatus("Choice selection: " + getSelectedItem());
            super.processItemEvent(ie);
        }
    }
}