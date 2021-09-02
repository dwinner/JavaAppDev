// Демонстрирует TextArea.
import java.awt.*;
import java.applet.*;

public class TextAreaDemo extends Applet
{
    @Override
    public void init()
    {
        String val = "Text\nText";
        TextArea text = new TextArea(val, 10, 30);
        add(text);
    }
}