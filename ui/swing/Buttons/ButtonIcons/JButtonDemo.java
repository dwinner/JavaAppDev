
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonDemo extends JFrame implements ActionListener
{
    private JTextField jtf;
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;

    public JButtonDemo()
    {
        // получить панель содержания
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // добавить кнопки в панель содержания
        ImageIcon france = new ImageIcon("france.gif");
        JButton jb = new JButton(france);
        jb.setActionCommand("France");
        jb.addActionListener(this);
        contentPane.add(jb);

        ImageIcon germany = new ImageIcon("germany.gif");
        jb = new JButton(germany);
        jb.setActionCommand("Germany");
        jb.addActionListener(this);
        contentPane.add(jb);

        ImageIcon italy = new ImageIcon("italy.gif");
        jb = new JButton(italy);
        jb.setActionCommand("Italy");
        jb.addActionListener(this);
        contentPane.add(jb);

        ImageIcon japan = new ImageIcon("jap.jpg");
        jb = new JButton(japan);
        jb.setActionCommand("Japan");
        jb.addActionListener(this);
        contentPane.add(jb);

        // Добавить текстовое поле в панель содержания
        jtf = new JTextField(15);
        contentPane.add(jtf);
    }

    public void actionPerformed(ActionEvent ae)
    {
        jtf.setText(ae.getActionCommand());
    }

    public static void main(String[] args)
    {
        JFrame theFrame = new JButtonDemo();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        theFrame.setVisible(true);
    }
}