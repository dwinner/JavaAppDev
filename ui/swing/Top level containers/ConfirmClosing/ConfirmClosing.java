// Подтверждение о выходе из приложения
import javax.swing.*;
import java.awt.event.*;

public class ConfirmClosing extends JFrame
{    
    public ConfirmClosing()
    {
        super("Application");
        // Отключаем операцию закрытия
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Добавляем слушателя событий окна
        addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent we)
            {   // Подтверждение выхода
                int res = JOptionPane.showConfirmDialog(null, "Действительно выйти?");
                if (res == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        
        // Выводим окно на экран
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ConfirmClosing();
    }
}