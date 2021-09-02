/*
 * �������� �������� ���������� ���������. 
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class SimpleTextEditor
{
    private JLabel jlabMsg;
    private JTextArea jta;
    private JTextField jtfFName;
    private JTextField jtfFind;
    private JButton jbtnSave;
    private JButton jbtnLoad;
    private JButton jbtnFind;
    private JButton jbtnFindNext;
    private int findIdx;

    public SimpleTextEditor()
    {
        // �������� ������ ��������� JFrame.
        JFrame jfrm = new JFrame("A Simple Text Editor");
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // ��������� ��������� �������� ������.
        jfrm.setSize(270, 420);
        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����������� ���������.
        jlabMsg = new JLabel();
        jlabMsg.setPreferredSize(new Dimension(200, 30));
        jlabMsg.setHorizontalAlignment(SwingConstants.CENTER);

        // �������� ����� ��� ������ ��� ��������� ������� ������������.
        JLabel jlabSeparator = new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200, 30));

        // �������� ����� Search For � Filename.
        JLabel jlabFind = new JLabel("Search For:");
        jlabFind.setPreferredSize(new Dimension(70, 20));
        jlabFind.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel jlabFilename = new JLabel("Filename:");
        jlabFilename.setPreferredSize(new Dimension(70, 20));
        jlabFilename.setHorizontalAlignment(SwingConstants.RIGHT);

        // �������� ��������� �������.
        jta = new JTextArea();

        // ��������� ��������� ������� � ������ ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jta);
        jscrlp.setPreferredSize(new Dimension(250, 200));

        // ���� �������������� ��� ����� ����� �����.
        jtfFName = new JTextField(15);

        /*
         * ���������� ����������� ������� ���������� ������� � ��������� ��������. ���������� ���������� ����� ��������
         * � ����� � ��������� ��� ������ ��� ������ ��������� ��������� ���������� �������. � ���������� findIdx
         * ������������ ������� ������� ���������� �������.
         */
        jta.addCaretListener(new CaretListener()
        {
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                String str = jta.getText();
                jlabMsg.setText("Current size: " + str.length());
                findIdx = jta.getCaretPosition();
            }
        });

        // �������� ������ Save File � Load File.
        jbtnSave = new JButton("Save File");
        jbtnLoad = new JButton("Load File");

        // ���������� ����������� ������� � ������� Save File.
        jbtnSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                save();
            }
        });

        // ���������� ����������� ������� � ������� Load File.
        jbtnLoad.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                load();
            }
        });

        // �������� ���� �������������� Search For.
        jtfFind = new JTextField(15);

        // �������� ������ Search From Top � Find Next.
        jbtnFind = new JButton("Find From Top");
        jbtnFindNext = new JButton("Find Next");

        // ���������� ����������� ������� � ������� Find From Top.
        jbtnFind.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                findIdx = 0;
                find(findIdx);
            }
        });

        // ���������� ����������� ������� � ������� Find Next.
        jbtnFindNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                find(findIdx + 1);
            }
        });

        // ���������� ����������� � ������ �����������.
        Container cp = jfrm.getContentPane();
        cp.add(jscrlp);
        cp.add(jlabFind);
        cp.add(jtfFind);
        cp.add(jbtnFind);
        cp.add(jbtnFindNext);
        cp.add(jlabSeparator);
        cp.add(jlabFilename);
        cp.add(jtfFName);
        cp.add(jbtnSave);
        cp.add(jbtnLoad);
        cp.add(jlabMsg);

        // ����������� ������
        jfrm.setVisible(true);
    }

    // �����, ��������������� ��� ���������� �����.
    void save()
    {
        FileWriter fw;
        // ���������� ����� ����� �� ���� ��������������.
        String fname = jtfFName.getText();
        // �������� ������� �����.
        if (fname.length() == 0)
        {
            jlabMsg.setText("No filename present.");
            return;
        }
        // ���������� �����.
        try
        {
            fw = new FileWriter(fname);
            jta.write(fw);
            fw.close();
        }
        catch (IOException exc)
        {
            jlabMsg.setText("Error opening or writing file.");
            return;
        }
        jlabMsg.setText("File written successfully.");
    }

    // �����, ��������������� ��� �������� �����.
    void load()
    {
        FileReader fw;
        // ���������� ����� ����� �� ���� ��������������.
        String fname = jtfFName.getText();
        // �������� ������� ����� �����.
        if (fname.length() == 0)
        {
            jlabMsg.setText("No filename present.");
            return;
        }
        // �������� �����.
        try
        {
            fw = new FileReader(fname);
            jta.read(fw, null);
            fw.close();
        }
        catch (IOException exc)
        {
            jlabMsg.setText("Error opening or reading file.");
            return;
        }
        // ��� �������� ������ ����� ������� ������� ����������.
        findIdx = 0;
        jlabMsg.setText("File loaded successfully.");
    }

    // ����� ������.
    void find(int start)
    {
        // ��������� �������� ������ � ���� ������.
        String str = jta.getText();
        // ���������� ������ ������.
        String findStr = jtfFind.getText();
        // ����� ������� ��������� ��������� ������.
        int idx = str.indexOf(findStr, start);
        // ��������, ������� �� ������������.
        if (idx > -1)
        {
            // ���� ����� �������� �������, ��������� ������ ���������� � �������,
            // ��������������� ���������� ������.
            jta.setCaretPosition(idx);
            findIdx = idx;
            jlabMsg.setText("String found.");
        }
        else
        {
            jlabMsg.setText("String not found.");
        }
        // �������� ������ ����� ���� ���������.
        jta.requestFocusInWindow();
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SimpleTextEditor();
            }
        });
    }
}