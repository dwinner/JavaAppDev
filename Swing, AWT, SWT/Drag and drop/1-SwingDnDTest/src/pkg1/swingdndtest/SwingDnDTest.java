package pkg1.swingdndtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * ƒанна€ программа демонстрирует поддерживаемые в Swing базовые возможности дл€ осуществлени€
 * перетаскивани€
 * <p/>
 * @version 1.10 2007-09-20
 * @author Cay Horstmann.
 */
public class SwingDnDTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SwingDnDFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SwingDnDFrame extends JFrame
{
    SwingDnDFrame() throws HeadlessException
    {
        setTitle("SwingDnDTest");
        JTabbedPane tabbedPane = new JTabbedPane();

        JList<? extends CharSequence> list = SampleComponents.list();
        tabbedPane.addTab("List", list);

        JTable table = SampleComponents.table();
        tabbedPane.addTab("Table", table);

        JTree tree = SampleComponents.tree();
        tabbedPane.addTab("Tree", tree);

        JFileChooser fileChooser = new JFileChooser();
        tabbedPane.addTab("File Chooser", fileChooser);

        JColorChooser colorChooser = new JColorChooser();
        tabbedPane.addTab("Color Chooser", colorChooser);

        final JTextArea textArea = new JTextArea(4, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Drag text here"));

        JTextField textField = new JTextField("Drag color here");
        textField.setTransferHandler(new TransferHandler("background"));

        tabbedPane.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                textArea.setText("");
            }
        });

        tree.setDragEnabled(true);
        table.setDragEnabled(true);
        list.setDragEnabled(true);
        fileChooser.setDragEnabled(true);
        colorChooser.setDragEnabled(true);
        textField.setDragEnabled(true);

        add(tabbedPane, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);

        pack();
    }
}

class SampleComponents
{
    public static JTree tree()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        city = new DefaultMutableTreeNode("Cupertino");
        state.add(city);
        state = new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswig-Holstein");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);
        return new JTree(root);
    }

    public static JList<CharSequence> list()
    {
        String[] words =
        {
            "quick", "brown", "hungry", "wild", "silent", "huge", "private",
            "abstract", "static", "final"
        };

        DefaultListModel<CharSequence> model = new DefaultListModel<>();
        for (String word : words)
        {
            model.addElement(word);
        }
        return new JList<>(model);
    }

    public static JTable table()
    {
        Object[][] cells =
        {
            {
                "Mercury", 2440.0, 0, false, Color.YELLOW
            },
            {
                "Venus", 6052.0, 0, false, Color.YELLOW
            },
            {
                "Earth", 6378.0, 1, false, Color.BLUE
            },
            {
                "Mars", 3397.0, 2, false, Color.RED
            },
            {
                "Jupiter", 71492.0, 16, true, Color.ORANGE
            },
            {
                "Saturn", 60268.0, 18, true, Color.ORANGE
            },
            {
                "Uranus", 25559.0, 17, true, Color.BLUE
            },
            {
                "Neptune", 24766.0, 8, true, Color.BLUE
            },
            {
                "Pluto", 1137.0, 1, false, Color.BLACK
            }
        };

        String[] columnNames =
        {
            "Planet", "Radius", "Moons", "Gaseous", "Color"
        };
        return new JTable(cells, columnNames);
    }
}