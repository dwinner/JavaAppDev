// Пример использования фиксированных областей
import javax.swing.*;
import java.awt.Dimension;

public class BoxRigidAreas extends JFrame
{    
    public BoxRigidAreas()
    {
        super("Box Rigid Area");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Вертикальная панель
        Box pVert = Box.createVerticalBox();
        pVert.add(new JButton("One"));
        
        // Горизонтальная панель
        Box pHor = Box.createHorizontalBox();
        pHor.add(new JButton("Two"));
        
        // Размер пространства задается в виде объекта Dimension
        pHor.add(Box.createRigidArea(new Dimension(50, 120)));
        pHor.add(new JButton("Three"));
        pVert.add(pHor);
        
        // Добавляем вертикальную панель в центр окна
        getContentPane().add(pVert);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BoxRigidAreas();
    }

}