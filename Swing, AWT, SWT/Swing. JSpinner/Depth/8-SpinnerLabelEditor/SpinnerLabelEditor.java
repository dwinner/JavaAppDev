// Редактор счетчика JSpinner на основе надписи
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerLabelEditor extends JFrame
{
    // Данные для счетчика
    private String[] data =
    {
        "Красный",
        "Зеленый",
        "Синий"
    };

    public SpinnerLabelEditor()
    {
        super("Spinner label editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем счетчик
        JSpinner spinner = new JSpinner(new SpinnerListModel(data));
        // Присоединяем наш редактор
        LabelEditor editor = new LabelEditor();
        spinner.setEditor(editor);
        // Регистрируем слушателя
        spinner.addChangeListener(editor);
        // Выводим окно на экран
        JPanel contents = new JPanel();
        contents.add(spinner);
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    /**
     * Специальный редактор для счетчика
     */
    private class LabelEditor extends JLabel implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            // Получаем счетчик
            JSpinner spinner = (JSpinner) e.getSource();
            // Получаем текущий элемент
            Object value = spinner.getValue();
            // Устанавливаем новое значение
            if (value.equals(data[0]))
            {
                setText("<html><h2><font color=\"red\">" + value + "</font></h2>");
            }
            else if (value.equals(data[1]))
            {
                setText("<html><h3><font color=\"green\">" + value + "</font></h2>");
            }
            else if (value.equals(data[2]))
            {
                setText("<html><h2><font color=\"blue\">" + value + "</font></h2>");
            }
            else
            {
                setText(value.toString());
            }
        }
        // Размер редактора

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(100, 30);
        }
    }

    public static void main(String[] args)
    {
        new SpinnerLabelEditor();
    }
}