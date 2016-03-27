
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JRadioButtonDemo extends JApplet implements ActionListener
{
    private JTextField tf;

    @Override
    public void init()
    {
        // получить панель содержания
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // добавить переключатели в панель содержания
        JRadioButton b1 = new JRadioButton("A");
        b1.addActionListener(this);
        contentPane.add(b1);

        JRadioButton b2 = new JRadioButton("B");
        b2.addActionListener(this);
        contentPane.add(b2);

        JRadioButton b3 = new JRadioButton("C");
        b3.addActionListener(this);
        contentPane.add(b3);

        // определить группу кнопок
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        // создать текстовое поле и добавить его в панель содержания
        tf = new JTextField(5);
        contentPane.add(tf);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        tf.setText(ae.getActionCommand());
    }

}