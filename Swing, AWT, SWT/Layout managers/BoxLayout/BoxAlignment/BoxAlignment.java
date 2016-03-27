// Блочное расположение обеспечивает выравнивание компонентов по осям
import javax.swing.*;

public class BoxAlignment extends JFrame
{
    public BoxAlignment()
    {
        super("Box Alignment");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Вертикальная панель
        Box pVert = Box.createVerticalBox();
        
        // Кнопка с выравниванием по левой границе
        JButton jb = new JButton("Левая граница");
        jb.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        pVert.add(jb);
        
        // Кнопка с центральным выравниванием
        jb = new JButton("Выравнивание по центру");
        jb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        pVert.add(jb);
        
        // Наконец, кнопка с выравниванием по правому краю
        jb = new JButton("Правая граница");
        jb.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        pVert.add(jb);
        
        // Добавляем панель в центр окна
        getContentPane().add(pVert);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BoxAlignment();
    }

}