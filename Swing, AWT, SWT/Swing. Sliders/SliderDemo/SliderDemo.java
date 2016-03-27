// Пример, демонстрирующий работу с объектами JSlider
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
 
    private JSlider jsldrHoriz;
    private JSlider jsldrVert; 
  
    public SliderDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Sliders");  
 
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout()); 
  
        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 300);  
  
        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // Создание вертикального и горизонтального линейного регулятора.
        jsldrVert = new JSlider(JSlider.VERTICAL); 
        jsldrHoriz = new JSlider(); // По умолчанию принимается горизонтальная ориентация компонента
 
        // Определение расположения основных маркеров для обоих компонентов.
        jsldrVert.setMajorTickSpacing(10); 
        jsldrHoriz.setMajorTickSpacing(20); 
 
        // Определение расположения вспомогательных маркеров для вертикального регулятора.
        jsldrVert.setMinorTickSpacing(5); 
 
        // Создание стандартных числовых меток.
        jsldrVert.setLabelTable(jsldrVert.createStandardLabels(10)); 
        jsldrHoriz.setLabelTable(jsldrHoriz.createStandardLabels(20)); 
 
        // Разрешение отображения маркеров.
        jsldrVert.setPaintTicks(true); 
        jsldrHoriz.setPaintTicks(true); 
 
        // Разрешение отображения меток.
        jsldrVert.setPaintLabels(true); 
        jsldrHoriz.setPaintLabels(true); 
 
        // Метки для представления текущего значения регуляторов.
        jlabHoriz = new JLabel("Value of horizontal slider: " + jsldrHoriz.getValue());
        jlabVert = new JLabel("Value of vertical slider: " + jsldrVert.getValue());
 
        // Обработчики событий изменения состояния для линейных регуляторов.
        // Перед тем как выполнить действие, соответствующее событию, горизонтальный
        // регулятор ожидает окончания действий пользователя.
        jsldrHoriz.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                // Если линейный регулятор претерпевает изменения, никакие действия не выполняются.
                if (jsldrHoriz.getValueIsAdjusting())
                {
                    return;
                }
                // Отображение нового значения.
                jlabHoriz.setText("Value of horizontal slider: " + jsldrHoriz.getValue());
            }
        });
 
        // Вертикальный линейный регулятор реагирует на все события изменения состояния,
        // независимо от того, перемещает ли пользователь ползунок компонента.
        jsldrVert.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                // Отображение нового значения.
                jlabVert.setText("Value of vertical slider: " + jsldrVert.getValue());
            }
        });
 
        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jsldrHoriz);
        jfrm.getContentPane().add(jsldrVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVert);
 
        // Отображение фрейма.
        jfrm.setVisible(true);
    }
 
    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new SliderDemo();
            }
        });
    }
}