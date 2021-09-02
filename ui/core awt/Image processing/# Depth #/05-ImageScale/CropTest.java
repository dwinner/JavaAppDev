/*
 * Приемы масштабирования изображений.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;

public class CropTest extends Frame
{
    private Image img, cropimg, replimg, averimg;

    public CropTest(String s)
    {
        super(s);
        // 1) Создаем изображение - объект класса Image
        img = getToolkit().getImage("Linux-logo.jpg");
        // 2) Создаем объекты-фильтры: а) Выделяем левый верхний угол размером 30x30
        CropImageFilter crp = new CropImageFilter(0, 0, 30, 30);
        // Уменьшаем изображение в два раза простым методом
        ReplicateScaleFilter rsf = new ReplicateScaleFilter(84, 114);
        // Уменьшаем изображение в два раза с усреднением
        AreaAveragingScaleFilter asf = new AreaAveragingScaleFilter(84, 114);
        // 3. Создаем измененные изображения
        cropimg = createImage(new FilteredImageSource(img.getSource(), crp));
        replimg = createImage(new FilteredImageSource(img.getSource(), rsf));
        averimg = createImage(new FilteredImageSource(img.getSource(), asf));
        setSize(400, 350);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(cropimg, 150, 40, 100, 100, this);
        g.drawImage(replimg, 10, 150, this);
        g.drawImage(averimg, 150, 150, this);
    }

    public static void main(String[] args)
    {
        Frame f = new CropTest("Масштабирование");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }
}