
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxDemo extends JFrame implements ItemListener
{
    private JLabel jl;
    private ImageIcon france, germany, italy, japan;

    @SuppressWarnings("unchecked")
    public JComboBoxDemo(String aTitle)
    {
        super(aTitle);
        // получить панель содержания
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // создать комбинированный список и добавить его в панель
        JComboBox<String> jc = new JComboBox<String>();
        jc.addItem("France");
        jc.addItem("Germany");
        jc.addItem("Italy");
        jc.addItem("Japan");
        jc.addItemListener(this);
        contentPane.add(jc);

        // создать метку
        jl = new JLabel(new ImageIcon("france.gif"));
        contentPane.add(jl);
    }

    public void itemStateChanged(ItemEvent ie)
    {
        String s = (String) ie.getItem();
        jl.setIcon(new ImageIcon(s + ".gif"));
    }

    public static void main(String[] args)
    {
        JFrame theFrame = new JComboBoxDemo("JComboBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(640, 480);
        theFrame.setVisible(true);
    }
}