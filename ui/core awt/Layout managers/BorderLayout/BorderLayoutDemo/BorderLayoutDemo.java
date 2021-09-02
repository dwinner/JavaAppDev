
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;

/**
 * Демонстрирует BorderLayout
 * <p/>
 * @author oracle_pr1
 */
public class BorderLayoutDemo extends Applet
{
    @Override
    public void init()
    {
        setLayout(new BorderLayout());

        add(new Button("This is across the top."), BorderLayout.NORTH);
        add(new Label("The footer message might go here."), BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "This is thw simple text.";

        add(new TextArea(msg), BorderLayout.CENTER);
    }

}