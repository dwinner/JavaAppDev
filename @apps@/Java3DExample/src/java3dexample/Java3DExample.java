package java3dexample;

import java.awt.*;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Java3DExample - приложение, демонстрирующее возможности Java 3D и
 * предоставляющее пользователю интерфейс для управления преобразованиями,
 * цветом освещения, и текстурами.
 * @author dwinner@inbox.ru
 */
public class Java3DExample extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Java3DExample.class.getName());
    
    private Java3DWorld java3DWorld;    // панель сцены
    private JPanel controlPanel;    // панель управления сценой
    
    // инициализация объектов Java3DWorld и ControlPanel
    public Java3DExample() {
        super("Java 3D Graphics Demo");
        
        java3DWorld = new Java3DWorld("images/andLinux_logo.jpg");
        controlPanel = new ControlPanel(java3DWorld);
        
        // Добавление компонентов в окно JFrame
        getContentPane().add(java3DWorld, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.EAST);
    }

    // Запуск программы на выполнение
    public static void main(String[] args) {
        Java3DExample app3D = new Java3DExample();
        app3D.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app3D.pack();
        app3D.setVisible(true);
    }
}
