
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class MyLookAndFeel extends BasicLookAndFeel
{
    public static final int DEFAULT_FRAME_WIDTH = 640;
    public static final int DEFAULT_FRAME_HEIGHT = 480;

    @Override
    public String getName()
    {   // ��� Look And Feel
        return "MyLookAndFeel";
    }

    @Override
    public String getID()
    {   // ���������� ������������� Look And Feel
        return getName();
    }

    @Override
    public String getDescription()
    {   // �������� Look And Feel
        return "Cross-platform Java Look And Feel";
    }

    @Override
    public boolean isNativeLookAndFeel()
    {   // �������� �� ������ Look And Feel � ������� ���������
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel()
    {   // �������������� �� ������ Look And Feel �� ������� ���������
        return true;
    }

    @Override
    protected void initClassDefaults(UIDefaults table)
    {
        // �� �������� ��������� ������������� �� ���������, ��� ��� �� ���� ���
        // �� ����������� ��� ��������� UI-������ ��� ���� J-�����������
        super.initClassDefaults(table);

        // ��� ������ ������ ���� UI-�������������
        table.put("ButtonUI", MyButtonUI.class.getCanonicalName());
    }

    public static void main(String[] args)
        throws ClassNotFoundException,
               InstantiationException,
               IllegalAccessException,
               UnsupportedLookAndFeelException
    {
        // ��������� ���������������� Look And Feel
        UIManager.setLookAndFeel(MyLookAndFeel.class.getCanonicalName());

        JFrame objFrame = new JFrame("Custom L&F");
        objFrame.setLayout(new FlowLayout());
        /*
         * objFrame.setUndecorated(true); objFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
         */

        objFrame.setBackground(Color.WHITE);
        final JButton jButton = new JButton("Custom L&F");
        objFrame.getContentPane().add(jButton);
        objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        objFrame.setVisible(true);
    }
}