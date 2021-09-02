
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class SplitScroll
{
    public SplitScroll()
    {
        JFrame jfrm = new JFrame("Split pane of scrolling");
        jfrm.setSize(280, 140);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание панели с прокруткой
        JLabel jlabOptions = new JLabel("Select one or more options: ");
        JCheckBox jcbOpt1 = new JCheckBox("Option one");
        JCheckBox jcbOpt2 = new JCheckBox("Option two");
        JCheckBox jcbOpt3 = new JCheckBox("Option three");
        JCheckBox jcbOpt4 = new JCheckBox("Option four");
        JCheckBox jcbOpt5 = new JCheckBox("Option five");
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(6, 1));
        jpnl.setOpaque(true);
        jpnl.add(jlabOptions);
        jpnl.add(jcbOpt1);
        jpnl.add(jcbOpt2);
        jpnl.add(jcbOpt3);
        jpnl.add(jcbOpt4);
        jpnl.add(jcbOpt5);
        JScrollPane jscrlp = new JScrollPane(jpnl);
        jscrlp.setMinimumSize(new Dimension(90, 30));

        JLabel jlabRight = new JLabel("Right from the split pane label");
        jlabRight.setMinimumSize(new Dimension(90, 30));

        // Включение разделяемой панели.
        JSplitPane jsp =
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jscrlp, jlabRight);
        jsp.setOneTouchExpandable(true);

        jfrm.getContentPane().add(jsp);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SplitScroll();
            }
        });
    }
}