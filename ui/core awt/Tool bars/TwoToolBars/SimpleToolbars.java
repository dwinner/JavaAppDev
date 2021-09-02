// ������� ������ ������������
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SimpleToolbars extends JFrame
{
    public SimpleToolbars()
    {
        super("Simple toolbars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������ ������ ������������
        JToolBar toolbar = new JToolBar();

        // ��������� ������
        toolbar.add(new JButton(new ImageIcon("images/new.jpg")));
        toolbar.add(new JButton(new ImageIcon("images/open.gif")));

        toolbar.addSeparator();

        // ��������� �������
        toolbar.add(new SaveAction());

        // ������ ������ ������������
        JToolBar toolbar2 = new JToolBar();

        // ��������� �������
        toolbar2.add(new SaveAction());

        // �������������� ������
        toolbar2.add(new JComboBox(new String[]
            {
                "Bold", "Oblique", "Italic"
            }));

        // ��������� ������ ������������ � ����
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(toolbar2, BorderLayout.SOUTH);

        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * ������� ��� ������ ������������
     */
    private class SaveAction extends AbstractAction
    {
        SaveAction()
        {
            // ����������� ������ �������
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.gif"));
            // ����� ���������
            putValue(AbstractAction.SHORT_DESCRIPTION, "Save Document");
        }

        public void actionPerformed(ActionEvent ae)
        {
            // ������ �� ������
        }
    }

    public static void main(String[] args)
    {
        new SimpleToolbars();
    }
}