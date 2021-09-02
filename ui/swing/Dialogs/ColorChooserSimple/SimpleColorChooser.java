// ����� ����� � ������� JColorChooser
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleColorChooser extends JFrame
{
    // ���� ������ �����������
    private JPanel contents = new JPanel();

    public SimpleColorChooser()
    {
        super("Simple Color Chooser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������ �������� �� ����� ���� ������ �����
        JButton chooseBtn = new JButton("����� ����� ����");
        chooseBtn.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                Color color = JColorChooser.showDialog(
                   SimpleColorChooser.this,
                   "�������� ���� ����",
                   contents.getBackground());
                // ���� ���� ������, ���������� ���
                if (color != null)
                {
                    contents.setBackground(color);
                    repaint();
                }
            }

        });

        // ������� ���� �� �����
        contents.add(chooseBtn);
        setContentPane(contents);
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleColorChooser();
    }

}