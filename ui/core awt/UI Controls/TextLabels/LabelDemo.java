// Демонстрирует метки.
import java.awt.*;
import java.applet.*;

/*
<applet code="LabelDemo" width="300" height="200">
</applet>
 */
public class LabelDemo extends Applet
{
    public void init()
    {
        Label one = new Label("One");
        Label two = new Label("Two");
        Label three = new Label("Three");

        // добавить метки в окно апплета
        add(one);
        add(two);
        add(three);
    }
}
