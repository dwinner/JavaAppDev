// Использование заполнителей
import javax.swing.*;

public class BoxGlues extends JFrame
{
    public BoxGlues()
    {
        super("Box Glues");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Панель с вертикальным блочным расположением, в нее поместим все остальные панели
        Box main = Box.createVerticalBox();
        
        // Вертикальная панель
        Box pVert = Box.createVerticalBox();
        
        // Заполнитель перед компонентами отодвинет их вниз
        pVert.add(Box.createVerticalGlue());
        pVert.add(new JButton("One"));
        pVert.add(new JButton("Two"));
        
        // Горизонтальная панель. Теперь можно разместить компоненты по центру JPanel
        Box pHor = Box.createHorizontalBox();
        pHor.add(Box.createHorizontalGlue());
        pHor.add(new JButton("Three"));
        pHor.add(new JButton("Four"));
        pHor.add(Box.createHorizontalGlue());
        
        // Укладываем панели вертикально
        main.add(pVert);
        main.add(Box.createVerticalStrut(15));
        main.add(pHor);
        
        // Добавляем панель в центр панели содержимого
        getContentPane().add(main);
        
        // Выводим окно на экран
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BoxGlues();
    }

}