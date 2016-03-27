// Перебор значений типа double.
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class DoubleSpin
{
    private JSpinner jspin;

    public DoubleSpin()
    {
        JFrame jfrm = new JFrame("Spin Doubles");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(200, 120);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpinnerNumberModel spm = new SpinnerNumberModel(4.0, 0.0, 9.9, 0.1);
        jspin = new JSpinner(spm);
        jspin.setPreferredSize(new Dimension(40, 20));
        jfrm.getContentPane().add(jspin);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DoubleSpin();
            }
        });
    }
}