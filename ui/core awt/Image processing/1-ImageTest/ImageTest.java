import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * @version 1.33 2007-04-14
 * @author Cay Horstmann
 */
public class ImageTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ImageFrame frame = new ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с компонентом, на котором выводится изображение.
 */
class ImageFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    public ImageFrame()
    {
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление компонента к фрейму.

        ImageComponent component = new ImageComponent();
        add(component);
    }
}

/**
 * Компонент, на котором многократно выводится изображение.
 */
class ImageComponent extends JComponent
{
    private Image image;
    
    public ImageComponent()
    {
        // Получение изображения.
        try
        {
            image = ImageIO.read(new File("blue-ball.gif"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        if (image == null)
            return;

        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // Отображение рисунка в левом верхнем углу.

        g.drawImage(image, 0, 0, null);
        // Многократный вывод изображения в панели.

        for (int i = 0; i * imageWidth <= getWidth(); i++)
            for (int j = 0; j * imageHeight <= getHeight(); j++)
                if (i + j > 0)
                    g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
    }
}
