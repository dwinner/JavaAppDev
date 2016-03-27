
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 * Программа создание цвета.
 * @author dwinner@inbox.ru
 * @version 1.0.0
 */
public class ScrollTest extends JFrame
{
    private JScrollBar sbRed = new JScrollBar(JScrollBar.VERTICAL, 127, 16, 0, 271);
    private JScrollBar sbGreen = new JScrollBar(JScrollBar.VERTICAL, 127, 16, 0, 271);
    private JScrollBar sbBlue = new JScrollBar(JScrollBar.VERTICAL, 127, 16, 0, 271);
    private Color c = new Color(127, 127, 127);
    private JLabel lm = new JLabel();
    private JButton b1 = new JButton("Apply");
    private JButton b2 = new JButton("Cancel");

    public ScrollTest(String s)
    {
        super(s);
        setLayout(null);
        setFont(new Font("Serif", Font.BOLD, 15));
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(10, 50, 150, 260);
        add(p);
        JLabel lc = new JLabel("Choose a color");
        lc.setBounds(20, 0, 120, 30);
        p.add(lc);
        JLabel lmin = new JLabel("0", JLabel.RIGHT);
        lmin.setBounds(0, 30, 30, 30);
        p.add(lmin);
        JLabel lmiddle = new JLabel("127", JLabel.RIGHT);
        lmiddle.setBounds(0, 120, 30, 30);
        p.add(lmiddle);
        JLabel lmax = new JLabel("255", JLabel.RIGHT);
        lmax.setBounds(0, 200, 30, 30);
        p.add(lmax);
        sbRed.setBackground(Color.red);
        sbRed.setBounds(40, 30, 20, 200);
        p.add(sbRed);
        sbRed.addAdjustmentListener(new ChColor());
        sbGreen.setBackground(Color.green);
        sbGreen.setBounds(70, 30, 20, 200);
        p.add(sbGreen);
        sbGreen.addAdjustmentListener(new ChColor());
        sbBlue.setBackground(Color.blue);
        sbBlue.setBounds(100, 30, 20, 200);
        p.add(sbBlue);
        sbBlue.addAdjustmentListener(new ChColor());
        JLabel lp = new JLabel("Sample");
        lp.setBounds(250, 50, 120, 30);
        add(lp);
        lm.setOpaque(true);
        lm.setBackground(new Color(127, 127, 127));
        lm.setBounds(250, 80, 120, 80);
        add(lm);
        b1.setBounds(240, 200, 100, 30);
        add(b1);
        b1.addActionListener(new ApplyColor());
        b2.setBounds(240, 240, 100, 30);
        add(b2);
        b2.addActionListener(new CancelColor());
        setSize(400, 300);
        setVisible(true);
    }

    private class ChColor implements AdjustmentListener
    {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            if (e.getAdjustable() == sbRed)
            {
                red = e.getValue();
            }
            if (e.getAdjustable() == sbGreen)
            {
                green = e.getValue();
            }
            if (e.getAdjustable() == sbBlue)
            {
                blue = e.getValue();
            }
            c = new Color(red, green, blue);
            lm.setBackground(c);
            lm.repaint();
        }

    }

    private class ApplyColor implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            getContentPane().setBackground(c);
            getContentPane().repaint();
        }

    }

    private class CancelColor implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            c = new Color(127, 127, 127);
            sbRed.setValue(127);
            sbGreen.setValue(127);
            sbBlue.setValue(127);
            lm.setBackground(c);
            setBackground(Color.white);
        }

    }

    public static void main(String[] args)
    {
        Frame f = new ScrollTest("Color Changer");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }

        });
    }

}