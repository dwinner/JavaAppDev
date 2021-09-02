import javax.swing.ImageIcon;

/**
 * Описание планеты.
 */
public class Planet
{
    private String name;
    private double radius;
    private int moons;
    private ImageIcon image;

    /**
     * Создание объекта, представляющего планету.
     * <p/>
     * @param aName   Название планеты
     * @param aRadius Радиус планеты
     * @param moons   Количество спутников
     */
    public Planet(String aName, double aRadius, int moons)
    {
        name = aName;
        radius = aRadius;
        this.moons = moons;
        image = new ImageIcon(name + ".gif");
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getDescription()
    {
        return "Radius: " + radius + "\nMoons: " + moons + "\n";
    }

    /**
     * Получение изображения планеты.
     * <p/>
     * @return Изображение
     */
    public ImageIcon getImage()
    {
        return image;
    }
}
