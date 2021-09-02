// MVC, ������������ ������ JList � ������� DefaultListModel

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Dummy demo of DefaultListModel
 * <p/>
 * @author dwinner@inbox.ru
 */
public class PhilosophersJList extends JFrame
{
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> philosophers;
    private JList<String> list;

    // ����������� PhilosophersJList
    public PhilosophersJList()
    {
        super("Favorite Philosophers");

        // �������� ������ DefaultListModel ��� �������� ���������
        philosophers = new DefaultListModel<>();
        philosophers.addElement("Socrates");
        philosophers.addElement("Platon");
        philosophers.addElement("Aristotel");
        philosophers.addElement("St. Thomas Aquinas");
        philosophers.addElement("Soren Kierkegaard");
        philosophers.addElement("Immanuel Kant");
        philosophers.addElement("Friedrich Nietzsche");
        philosophers.addElement("Hannah Arendt");
        
        // �������� ������� JList ��� ����������� ��������� �� DefaultListModel
        list = new JList<>(philosophers);
        
        // ��������� �������� ������ ������ �������� �� ���
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // �������� ������ JButton ��� ���������� ���������
        JButton addButton = new JButton("Add Philosopher");
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ������ � ������������ ����� ������ ��������
                String name = JOptionPane.showInputDialog(PhilosophersJList.this, "Enter Name");
                // ���������� ������ �������� � ������
                philosophers.addElement(name);
            }
        });

        // �������� ������ JButton ��� �������� ���������� ��������
        JButton removeButton = new JButton("Remove Selected Philosopher");
        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // �������� ���������� �������� �� ������
                philosophers.removeElement(list.getSelectedValue());
            }
        });

        // ���������� ����������� ����������������� ����������
        JPanel inputPanel = new JPanel();
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        Container contentPane = getContentPane();
        contentPane.add(list, BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String args[])
    {
        new PhilosophersJList();
    }
}