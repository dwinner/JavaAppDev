// ������� ��������������� ������
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;

public class ComboBoxEvents extends JFrame
{    
    // ������ ��� �������
    private String[] data =
    {
        "�������",
        "������",
        "������",
        "�����"
    };
    
    public ComboBoxEvents()
    {
        super("ComboBox Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������ ������
        JComboBox combo1 = new JComboBox(data);
        
        // ��������� ����� ���������� ��������
        combo1.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                // ��������, ��� ���������
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    // ���������� ��������� �������
                    final Object item = e.getItem();
                    EventQueue.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            JOptionPane.showMessageDialog(ComboBoxEvents.this, item);
                        }
                    });
                }
            } 
        });
        
        // ������, ����������� ��������������
        final JComboBox combo2 = new JComboBox(data);
        combo2.setEditable(true);
        
        // ��������� ��������� ��������������
        combo2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // ���������� ����� ������������
                final Object item = combo2.getModel().getSelectedItem();
                // ������ ���������� ����
                combo2.hidePopup();
				// ���������� ���� ������, ��� ��� � �������� � EventQueue ��� ��� �������������
                /*EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        */JOptionPane.showMessageDialog(ComboBoxEvents.this, item);/*
                    }
                });*/
            }            
        });
        
        // ��������� ������ � ����
        JPanel contents = new JPanel();
        contents.add(combo1);
        contents.add(combo2);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(350, 250);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ComboBoxEvents();
    }

}