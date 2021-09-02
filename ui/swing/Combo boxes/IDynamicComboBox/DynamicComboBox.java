import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DynamicComboBox
{
    private JComboBox<String> jcbb;
    private JLabel jlab;
    private JButton jbtnRemove;
    private JList<String> jlst;

    // Создание массива с названиями сортов яблок.
    private String apples[] =
    {
        "Winesap",
        "Cortland",
        "Red Delicious",
        "Golden Delicious",
        "Gala",
        "Fuji",
        "Granny Smith",
        "Jonathan"
    };

    public DynamicComboBox()
    {
        JFrame jfrm = new JFrame("Dynamic JComboBox");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(220, 240);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jcbb = new JComboBox<String>(apples);
        jcbb.setEditable(true);

        jlab = new JLabel();

        jcbb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                String item = (String) jcbb.getSelectedItem();
                if (item == null)
                    return;
                jlab.setText("Current selection " + item);
                int i;
                for (i=0; i < jcbb.getItemCount(); i++)
                {
                    if (item.equals(jcbb.getItemAt(i)))
                    {
                        break;
                    }
                }
                if (i == jcbb.getItemCount()) 
                    jcbb.addItem(item);
            }
        });

        jcbb.setSelectedIndex(0);

        jbtnRemove = new JButton("Remove Selection");
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        jlst = new JList<String>(dlm);
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jsp = new JScrollPane(jlst);
        jsp.setPreferredSize(new Dimension(120, 90));
        
        jbtnRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                String item = (String) jcbb.getSelectedItem();
                if (item == null)
                    return;
                jcbb.removeItem(item);
                jlab.setText("Removed " + item);
                // Добавление удаленного пункта к списку
                DefaultListModel<String> dlm = (DefaultListModel<String>) jlst.getModel();
                dlm.addElement(item);
            }
        });

        jfrm.getContentPane().add(jcbb);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jbtnRemove);
        jfrm.getContentPane().add(jsp);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new DynamicComboBox();
            }
        });
    }
}