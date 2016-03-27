// Демонстрирует GridLayout.
import java.awt.*;
import java.awt.event.*;

public class GridLayoutDemo extends Frame
{
    static final int n = 4;

    public GridLayoutDemo(String title)
    {
        super(title);
        setSize(300, 200);
        setLayout(new GridLayout(n, n));

        setFont(new Font("SansSerif", Font.BOLD, 24));

        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                int k = i*n + j;
                if (k > 0)
                {
                    add(new Button("" + k));
                }
            }
        }
        
        setVisible(true);
    }

    public static void main(String args[])
    {
        GridLayoutDemo gld = new GridLayoutDemo("GridLayout Manager");
        gld.addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
}