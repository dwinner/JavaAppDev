/*
 * ������ � "�������������" JViewport
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
        // ������� � ������� �������� �������
        JLabel bigLabel = new JLabel("<html><h1>������� �������</h1><br />����� ������!");
        // ������������
        JViewport viewport = new JViewport();
        // ������������� ��� � ������� ��������
        viewport.setView(bigLabel);
        viewport.setExtentSize(new Dimension(100, 60));
        // ����� ������ ������� �������
        viewport.setViewPosition(new Point(50, 50));
        // ������������ ������ "������������"
        viewport.setPreferredSize(new Dimension(100, 60));
        viewport.setBackground(Color.WHITE);
        // ������� ���� �� �����
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