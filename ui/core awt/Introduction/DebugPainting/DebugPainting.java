// ������������ ������������ ������� ������� � Swing
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class DebugPainting extends JFrame
{
    public DebugPainting()
    {
        super("Debug Painting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PaintingComponent pc = new PaintingComponent();
        getContentPane().add(pc);
        // �������� ��� ���� ������� �������
        RepaintManager.currentManager(null).setDoubleBufferingEnabled(false);
    }

    // ���������, ������� ���-�� ������
    private class PaintingComponent extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // ��� ������� ������
            g.setColor(Color.orange);
            g.fillRect(0, 10, 100, 100);
            g.setColor(Color.green);
            g.drawOval(50, 50, 50, 50);
            g.setColor(Color.blue);
            g.fillOval(0, 20, 50, 50);
        }

    }

    public static void main(String[] args)
    {
        new DebugPainting();
    }

}