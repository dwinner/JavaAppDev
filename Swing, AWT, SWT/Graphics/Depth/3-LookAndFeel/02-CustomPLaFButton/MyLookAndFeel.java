
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
    {   // Имя Look And Feel
        return "MyLookAndFeel";
    }

    @Override
    public String getID()
    {   // Уникальный идентификатор Look And Feel
        return getName();
    }

    @Override
    public String getDescription()
    {   // Описание Look And Feel
        return "Cross-platform Java Look And Feel";
    }

    @Override
    public boolean isNativeLookAndFeel()
    {   // Привязан ли данный Look And Feel к текущей платформе
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel()
    {   // Поддерживается ли данный Look And Feel на текущей платформе
        return true;
    }

    @Override
    protected void initClassDefaults(UIDefaults table)
    {
        // По прежнему оставляем инициализацию по умолчанию, так как мы пока что
        // не реализовали все различные UI-классы для всех J-компонентов
        super.initClassDefaults(table);

        // Для кнопки задаем свой UI-представитель
        table.put("ButtonUI", MyButtonUI.class.getCanonicalName());
    }

    public static void main(String[] args)
        throws ClassNotFoundException,
               InstantiationException,
               IllegalAccessException,
               UnsupportedLookAndFeelException
    {
        // Установим пользовательский Look And Feel
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