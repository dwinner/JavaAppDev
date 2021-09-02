// Порядок работы рисующих методов Swing.
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SwingPainting extends JFrame
{
    public SwingPainting()
    {
        super("Swing Painting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new SwingComponent());
        setSize(200, 200);
        setVisible(true);
    }

    // Компонент, использующий возможности Swing
    private class SwingComponent extends JComponent
    {
        // Прорисовка компонента
        @Override
        public void paintComponent(Graphics g)
        {
            System.out.println("paintComponent");
            super.paintComponent(g);
            g.setColor(Color.red);
            g.fillOval(10, 10, 50, 50);
        }

        // Прорисовка рамки
        @Override
        public void paintBorder(Graphics g)
        {
            System.out.println("Paint Border");
            super.paintBorder(g);
        }

        // Прорисовка потомков
        @Override
        public void paintChildren(Graphics g)
        {
            System.out.println("Paint Children");
            super.paintChildren(g);
        }

    }

    public static void main(String[] args)
    {
        new SwingPainting();
    }

}