import javax.swing.ImageIcon;

/**
 * �������� �������.
 */
public class Planet
{
    private String name;
    private double radius;
    private int moons;
    private ImageIcon image;

    /**
     * �������� �������, ��������������� �������.
     * <p/>
     * @param aName   �������� �������
     * @param aRadius ������ �������
     * @param moons   ���������� ���������
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
     * ��������� ����������� �������.
     * <p/>
     * @return �����������
     */
    public ImageIcon getImage()
    {
        return image;
    }
}
