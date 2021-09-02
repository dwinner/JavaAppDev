
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MyContentPanel extends JPanel
{
    private JLabel jlab;
    private JButton jbtnRed;
    private JButton jbtnBlue;

    MyContentPanel()
    {
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        jlab = new JLabel("Select Border Color");
        jbtnRed = new JButton("Red");
        jbtnBlue = new JButton("Blue");

        // Добавляем подсказки к кнопкам.
        jbtnRed.setToolTipText(TOOL_TIP_TEXT_KEY);
        jbtnBlue.setToolTipText(jbtnBlue.getText());

        jbtnRed.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            }
        });

        jbtnBlue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            }
        });

        add(jbtnRed);
        add(jbtnBlue);
        add(jlab);
    }
}

public class CustomCPDemo
{
    public CustomCPDemo()
    {
        JFrame jfrm = new JFrame("Set the Content Pane");
        jfrm.setSize(240, 150);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyContentPanel mcp = new MyContentPanel();
        jfrm.setContentPane(mcp);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new CustomCPDemo();
            }
        });
    }
}