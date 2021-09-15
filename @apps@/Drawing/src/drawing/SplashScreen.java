package drawing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * SplashScreen ��������� ����������� ����� showSplash ��� 
 * ����������� ���������� ������ ����������.
 * @author dwinner@inbox.ru
 *
 */
public class SplashScreen {
	
    private JWindow window;
    private Timer timer;
    
    /**
     * ����������� SplashScreen
     * @param component ���������, ����������� � ����
     */
    public SplashScreen(Component component) {
        // �������� ������ ���� JWindow ��� �������� ��������
        window = new JWindow();        
        // ���������� ����������-��������� � ���� JWindow
        window.getContentPane().add(component);
        // ���� ����������� ������������ ���������� �������� SplashScreen ������� ����
        window.addMouseListener(new MouseAdapter() {
            // ��� ������� ������������� ������ ���� �� SplashScreen ������ � ������� ���� JWindow
            @Override public void mousePressed(MouseEvent me) {
                window.setVisible(false);
                window.dispose();
            }
        });
        // ���������� ������� ���� JWindow ��� ��������� ���������� Component
        window.pack();        
        // �������� ������� ������ ������������
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();        
        // ���������� ��������� x � y ��� ������������� ��������
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;        
        // ������� �������� � ��������� ����
        window.setBounds(x, y, width, height);
    }
    
    /**
     * ���������� �������� � ������� ��������� �������
     * @param delay ����� �������� ���� � �������������
     */
    public void showSplash(int delay) {
        // ����������� ����
        window.setVisible(true);        
        // �������� � ������ ������ ������� ��� �������� �������� SplashScreen ����� ���������
        // ������� ��������
        timer = new Timer(delay, new ActionListener() {
            @Override public void actionPerformed(ActionEvent ae) {
                // ������� � �������� ����
                window.setVisible(false);
                timer.stop();
            }           
        });
        timer.start();
    }
    
    /**
     * ������� true, ���� ���� �������� �������� �������
     * @return ���� ��������� ����
     */
    public boolean isVisible() {
        return window.isVisible();
    }
    
}