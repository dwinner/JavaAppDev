import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Этапы создания UI на примере диалогового окна входа в систему
 * @author dwinner@inbox.ru
 */
public class LoginDialog extends JDialog
{
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton ok;
    private JButton cancel;

    public LoginDialog(Frame owner)
    {
        super(owner, "Вход в систему");
        // При выходе из диалогового окна работа заканчивается
        addWindowListener(new WindowAdapter()
            {
                @Override public void windowClosing(WindowEvent e)
                {
                    dispose();
                    System.exit(0);
                }
            }
        );
        
        // Добавляем расположение в центр окна
        getContentPane().add(createUI());
        // Задаем предпочтительный размер
        pack();
        // Выводим окно на экран
        setVisible(true);
    }
    
    private Box createUI()
    {
        // 1) Создаем панель, которая будет содержать все остальные элементы
        // и панели расположения
        Box main = Box.createVerticalBox();
        
        // Отделение вертикального бокса от границ окна на 12 пикселей. Используем пустую рамку
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        main.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        
        // 2) Поочередно создаем "полосы", на которые был разбит интерфейс
        Box name = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("Test");
        name.add(nameLabel);
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        name.add(Box.createHorizontalStrut(12));
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        nameField = new JTextField(15);
        name.add(nameField);
        
        // Второе текстовое поле и надпись к нему
        Box password = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Пароль");
        password.add(passwordLabel);
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        password.add(Box.createHorizontalStrut(12));
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        passwordField = new JPasswordField(15);
        password.add(passwordField);
        
        // Ряд кнопок
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        ok = new JButton("OK");
        cancel = new JButton("Отмена");
        grid.add(ok);
        grid.add(cancel);
        flow.add(grid);
        
        // Действия по выравниванию компонентов, уточнению их размеров и т.д.
        // Согласованное выравнивание вложенных панелей
        BoxLayoutUtils.setGroupAlignmentX(
            new JComponent[] { name, password, main, flow },
            Component.LEFT_ALIGNMENT
        );
        
        // Центральное выравнивание надписей и текстовых полей
        BoxLayoutUtils.setGroupAlignmentY(
            new JComponent[] { nameLabel, passwordLabel, nameField, passwordField },
            Component.CENTER_ALIGNMENT
        );
        
        // Одинаковые размеры надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[] { nameLabel, passwordLabel } );
        
        // Стандартный вид для кнопок
        GUITools.createRecommendedMargin(new JButton[] { ok, cancel });
        
        // Устранение "бесконечной" высоты текстовых полей
        GUITools.fixTextFieldSize(nameField);
        GUITools.fixTextFieldSize(passwordField);
        
        // Сбор полос в интерфейсе
        main.add(name);
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        main.add(Box.createVerticalStrut(12));
        main.add(password);
        // TODO: Заменить числовые значения значениями из перечисления MetalPlafEnum
        main.add(Box.createVerticalStrut(12));
        main.add(flow);
        
        return main;
    }
   
}
