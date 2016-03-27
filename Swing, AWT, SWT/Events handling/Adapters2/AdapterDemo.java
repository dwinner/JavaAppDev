// Демонстрирует адаптер.
import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/*
 * <applet code="AdapterDemo" width="200" height="100"> </applet>
 */
public class AdapterDemo extends Applet
{
    @Override
    public void init()
    {
        addMouseListener(new MyMouseAdapter(this));
        addMouseMotionListener(new MyMouseMotionAdapter(this));
    }

}

class MyMouseAdapter extends MouseAdapter
{
    private AdapterDemo adapterDemo;

    MyMouseAdapter(AdapterDemo adapterDemo)
    {
        this.adapterDemo = adapterDemo;
    }

    // Обработка щелчка мыши.
    @Override
    public void mouseClicked(MouseEvent me)
    {
        adapterDemo.showStatus("Mouse clicked.");
    }

}

class MyMouseMotionAdapter extends MouseMotionAdapter
{
    private AdapterDemo adapterDemo;

    MyMouseMotionAdapter(AdapterDemo adapterDemo)
    {
        this.adapterDemo = adapterDemo;
    }

    // Обработка перетаскивания мыши
    @Override
    public void mouseDragged(MouseEvent me)
    {
        adapterDemo.showStatus("Mouse dragged.");
    }

}
