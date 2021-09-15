package drawing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * SplashScreen реализует статический метод showSplash для 
 * отображения начального экрана приложения.
 * @author dwinner@inbox.ru
 *
 */
public class SplashScreen {
	
    private JWindow window;
    private Timer timer;
    
    /**
     * Конструктор SplashScreen
     * @param component Компонент, добавляемый в окно
     */
    public SplashScreen(Component component) {
        // Создание нового окна JWindow для экранной заставки
        window = new JWindow();        
        // Добавление компонента-параметра в окно JWindow
        window.getContentPane().add(component);
        // Дать возможность пользователю пропускать заставку SplashScreen щелчком мыши
        window.addMouseListener(new MouseAdapter() {
            // При нажатии пользователем кнопки мыши на SplashScreen скрыть и закрыть окно JWindow
            @Override public void mousePressed(MouseEvent me) {
                window.setVisible(false);
                window.dispose();
            }
        });
        // Установить размеры окна JWindow для заданного компонента Component
        window.pack();        
        // Получить размеры экрана пользователя
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();        
        // Вычисление координат x и y для центрирования заставки
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;        
        // Задание размеров и положения окна
        window.setBounds(x, y, width, height);
    }
    
    /**
     * Отобразить заставку в течение заданного времени
     * @param delay Время задержки окна в миллисекундах
     */
    public void showSplash(int delay) {
        // Отображение окна
        window.setVisible(true);        
        // Создание и запуск нового таймера для удаления заставки SplashScreen после истечения
        // времени задержки
        timer = new Timer(delay, new ActionListener() {
            @Override public void actionPerformed(ActionEvent ae) {
                // Скрытие и закрытие окна
                window.setVisible(false);
                timer.stop();
            }           
        });
        timer.start();
    }
    
    /**
     * Возврат true, если окно заставки является видимым
     * @return Флаг видимости окна
     */
    public boolean isVisible() {
        return window.isVisible();
    }
    
}