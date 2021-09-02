// �������� �������� ���������� ���������
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
    private JTextField jtfReplace;
    private JButton jbtnSave;
    private JButton jbtnLoad;
    private JButton jbtnFind;
    private JButton jbtnFindNext;
    private JButton jbtnReplaceNext;
    private JButton jbtnReplaceAll;
    private JCheckBox jcbIgnoreCase;
    private int findIdx;

    SimpleTextEditor()
    {
        // �������� ������ ��������� JFrame.
        JFrame jfrm = new JFrame("A Simple Text Editor");
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // ��������� ��������� �������� ������.
        jfrm.setSize(280, 520);
        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����������� ���������.
        jlabMsg = new JLabel();
        jlabMsg.setPreferredSize(new Dimension(230, 30));
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

        // �������� ����� Replace For
        JLabel jlabReplace = new JLabel("Replace For:");
        jlabReplace.setPreferredSize(new Dimension(70, 20));
        jlabReplace.setHorizontalAlignment(SwingConstants.RIGHT);

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
                int wordCount = wordCount(str);
                jlabMsg.setText("Current count of words is: " + wordCount);
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

        // �������� ���� �������������� Replace For
        jtfReplace = new JTextField(15);

        // �������� ������ Search From Top � Find Next.
        jbtnFind = new JButton("Find From Top");
        jbtnFind.setMnemonic('F');
        jbtnFindNext = new JButton("Find Next");
        jbtnFindNext.setMnemonic('N');

        // �������� ������ Replace Next � Replace All
        jbtnReplaceNext = new JButton("Replace Next");
        jbtnReplaceAll = new JButton("Replace All");

        // �������� ������������� ������ ��� ����� �������� ��������
        jcbIgnoreCase = new JCheckBox("Ignore case search");
        jcbIgnoreCase.setPreferredSize(new Dimension(150, 20));

        // ���������� ����������� ������� � ������� Find From Top.
        jbtnFind.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                findIdx = 0;
                boolean ignoreCaseFlag = jcbIgnoreCase.isSelected() ? true : false;
                find(findIdx, ignoreCaseFlag);
            }
        });

        // ���������� ����������� ������� � ������� Find Next.
        jbtnFindNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                boolean ignoreCaseFlag = jcbIgnoreCase.isSelected() ? true : false;
                find(findIdx + 1, ignoreCaseFlag);
            }
        });

        // ���������� ����������� ������� � ������� Replace Next.
        jbtnReplaceNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                replace(false);
            }
        });

        // ���������� ����������� ������� � ������� Replace All.
        jbtnReplaceAll.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int replaceCount = replace(true);
                jlabMsg.setText("Replacement count is: " + replaceCount);
            }
        });

        // ���������� ����������� � ������ �����������.
        Container cp = jfrm.getContentPane();
        cp.add(jscrlp);
        cp.add(jlabFind);
        cp.add(jtfFind);
        cp.add(jbtnFind);
        cp.add(jbtnFindNext);
        cp.add(jcbIgnoreCase);
        cp.add(jlabReplace);
        cp.add(jtfReplace);
        cp.add(jbtnReplaceNext);
        cp.add(jbtnReplaceAll);
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
    void find(int start, boolean ignoreCase)
    {
        // ��������� �������� ������ � ���� ������.
        String str = jta.getText();
        // ���������� ������ ������.
        String findStr = jtfFind.getText();
        if (ignoreCase)
        {
            str = str.toLowerCase();
            findStr = findStr.toLowerCase();
        }
        // ����� ������� ��������� ��������� ������.
        int idx = str.indexOf(findStr, start);
        // ��������, ������� �� ������������.
        if (idx > -1)
        {
            // ���� ����� �������� �������, ��������� ������ ���������� � �������,
            // ��������������� ���������� ������.
            jta.setCaretPosition(idx);
            jta.moveCaretPosition(idx + findStr.length());
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

    int replace(boolean all)
    {
        int replaceCount = 0;   // ���������� �����
        if (all)
        {  // ������ ���� ���������
            // ��������� �������� ������ � ���� ������
            String str = jta.getText();
            // ���������� ������ ������
            String findStr = jtfFind.getText();
            // ���������� ������ ������
            String replaceStr = jtfReplace.getText();
            if (!findStr.isEmpty() && !replaceStr.isEmpty())
            {
                String result = ""; // �������������� ������ ����� ������
                int i;
                do
                {    // �������� ��� ���������
                    i = str.indexOf(findStr);
                    if (i != -1)
                    {
                        result = str.substring(0, i);
                        result += replaceStr;
                        result += str.substring(i + findStr.length());
                        str = result;
                        replaceCount++;
                    }
                }
                while (i != -1);
                jta.setText(str);
                jta.requestFocusInWindow();
            }
        }
        else
        {  // ������ �������� ���������
            int selectedRange = jta.getSelectionEnd() - jta.getSelectionStart();
            if (selectedRange > 0)
            {
                String str = jta.getText();
                String findStr = jtfFind.getText();
                String replaceStr = jtfReplace.getText();
                String result = "";
                int i = str.indexOf(findStr);
                if (i != -1)
                {
                    result = str.substring(0, i);
                    result += replaceStr;
                    result += str.substring(i + findStr.length());
                    str = result;
                    jta.setText(str);
                    jta.requestFocusInWindow();
                }
            }
        }
        return replaceCount;
    }

    // ������� ���� � ������
    int wordCount(String str)
    {
        int wc;
        if (str.length() == 0)
        {
            wc = 0;
        }
        else
        {
            String[] strsplit = str.split("\\W+");
            wc = strsplit.length;
            if (strsplit.length > 0 && strsplit[0].length() == 0)
            {
                wc--;
            }
            if (strsplit.length > 0 && strsplit[strsplit.length - 1].length() == 0)
            {
                wc--;
            }
        }
        return wc;
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