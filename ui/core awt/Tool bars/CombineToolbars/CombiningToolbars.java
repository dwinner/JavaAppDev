// �������� ��������������� ������� ������������
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CombiningToolbars extends JFrame
{
    public CombiningToolbars()
    {
        super("Combining toolbars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ��������� ������� ������������
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(new OpenAction());
        toolbar1.add(new SaveAction());
        toolbar1.addSeparator();
        toolbar1.add(new JButton("Style"));

        JToolBar toolbar2 = new JToolBar();
        toolbar2.add(new JButton("Topics"));
        toolbar2.add(new JComboBox<>(new String[]
            {
                "Red", "Green", "Blue"
            }));

        JToolBar toolbar3 = new JToolBar();
        toolbar3.add(new JButton("Normal"));
        toolbar3.add(new JButton("Bold"));
        toolbar3.add(new JButton("Underline"));

        // ��������� ��� ������ ������������ ����
        Box first = Box.createHorizontalBox();
        first.add(toolbar1);
        first.add(Box.createHorizontalStrut(5));
        first.add(toolbar2);

        // ����������� ���������� ������
        Box all = Box.createVerticalBox();
        all.add(first);
        all.add(toolbar3);
        getContentPane().add(all, BorderLayout.NORTH);

        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    // ��������� ������ ��� ������� ������������
    private class OpenAction extends AbstractAction
    {
        OpenAction()
        {
            // ����������� ������ �������
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.gif"));
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // ������ �� ������
        }
    }

    private class SaveAction extends AbstractAction
    {
        SaveAction()
        {
            // ����������� ������ �������
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.gif"));
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // ������ �� ������
        }
    }

    public static void main(String[] args)
    {
        new CombiningToolbars();
    }
}