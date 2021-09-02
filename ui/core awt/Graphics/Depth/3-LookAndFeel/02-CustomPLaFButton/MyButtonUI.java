
import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonUI extends BasicButtonUI
{
    private static MyButtonUI instance = null;

    @Override
    public void installUI(JComponent c)
    {
        // Обязательно оставляем установку UI, реализованную в Basic UI классе
        super.installUI(c);

        // Устанавливаем желаемые настройки для JButton
        // Для абстракции используем AbstractButton, так как в нем есть
        // все, что нам необходимо
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setFocusable(true);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

    }

    /**
     * "Заглушим" тот же метод с той-же сигнатурой, определенный для базового класса BasicButtonUI, иначе ничего не
     * изменится и мы вернем BasicButtonUI вместо собственного UI-представителя.
     * @param c UI-компонент
     * @return  UI-представитель компонента
     */
    public static ComponentUI createUI(JComponent c)
    {
        if (instance == null)
        {
            instance = new MyButtonUI();
        }
        return new MyButtonUI();
    }

    @Override
    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractButton button = (AbstractButton) c;
        ButtonModel buttonModel = button.getModel();

        // Формой кнопки будет закругленный прямоугольник

        // Фон кнопки
        g2D.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(), new Color(200, 200, 200)));

        // Закругление необходимо делать больше, чем при отрисовке формы,
        // иначе светлый фон будет просвечивать по краям
        g2D.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);

        // Рамка для кнопки
        g2D.setPaint(Color.GRAY);

        // Важно помнить, что форму необходимо делать на 1px меньше, чем ширина/высота компонента,
        // иначе правый и нижний края фигуры вылезут за границу компонента и не будут видны.
        // К заливке это не относится, так как последняя колонка/строка пикселей игнорируется при заполнении
        g2D.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 6, 6);

        // Сдвиг отрисовки при нажатой кнопке
        if (buttonModel.isPressed())
        {
            g2D.translate(1, 1);
        }

        // Отрисовка текста и иконки изображения
        super.paint(g, c);
    }
}