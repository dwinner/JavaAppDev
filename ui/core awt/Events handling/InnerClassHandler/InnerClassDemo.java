// ������������ ����������� ������.
import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * <applet code="InnerClassDemo" width="200" height="100"> </applet>
 */
public class InnerClassDemo extends Applet
{
    @Override
    public void init()
    {
        addMouseListener(new MyMouseAdapter());
    }

    private class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent me)
        {
            showStatus("Mouse Pressed");
        }

    }

}
