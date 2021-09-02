// ƒемонстраци€ анонимного внутреннего класса.
import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * <applet code="AnonymousInnerClassDemo" width="200" height="100"> </applet>
 */
public class AnonymousInnerClassDemo extends Applet
{
    @Override
    public void init()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                showStatus("Mouse Pressed");
            }

        });
    }

}
