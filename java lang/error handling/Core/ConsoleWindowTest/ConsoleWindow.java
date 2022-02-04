
import javax.swing.*;
import java.io.*;

/**
����, ��������������� ��� ����������� ����������,
���������� � ������ System.out � System.err
@version 1.01 2004-05-10
@author Cay Horstmann
 */
public class ConsoleWindow
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    public static final int DEFAULT_LEFT = 200;
    public static final int DEFAULT_TOP = 200;

    public static void init()
    {
        JFrame frame = new JFrame();
        frame.setTitle("ConsoleWindow");
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.add(new JScrollPane(output));
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocation(DEFAULT_LEFT, DEFAULT_TOP);
        frame.setFocusableWindowState(false);
        frame.setVisible(true);

        // ������������ ����� PrintStream, ��������������� ��� ������
        // ���������� � ��������� �������.
        PrintStream consoleStream = new PrintStream(new OutputStream()
        {
            public void write(int b){ } // �� ����������

            @Override public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));
            }
        });

        // ������ System.out � System.err ����� ������������ � ���� �����.
        System.setOut(consoleStream);
        System.setErr(consoleStream);
    }
}
