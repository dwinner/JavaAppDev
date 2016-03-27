/*
 * Работа с "видоискателем" JViewport
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JViewport;

public class UsingViewport extends JFrame
{
    public UsingViewport()
    {
        super("UsingViewport");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Надпись с текстом большого размера
        JLabel bigLabel = new JLabel("<html><h1>Большая надпись</h1><br />Много текста!");
        // Видоискатель
        JViewport viewport = new JViewport();
        // Устанавливаем вид и видимый диапазон
        viewport.setView(bigLabel);
        viewport.setExtentSize(new Dimension(100, 60));
        // Точка начала видимой области
        viewport.setViewPosition(new Point(50, 50));
        // Ограничивает размер "видоискателя"
        viewport.setPreferredSize(new Dimension(100, 60));
        viewport.setBackground(Color.WHITE);
        // Выводим окно на экран
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(viewport);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingViewport();
    }
}