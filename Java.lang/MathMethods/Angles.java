// Демонстрирует методы toDegrees() и toRadians().
public class Angles
{
    public static void main(String args[])
    {
        double theta = 120.0;

        System.out.println(theta + " degrees is " + Math.toRadians(theta) + " radians.");

        theta = Math.PI / 2;
        System.out.println(theta + " radians is " + Math.toDegrees(theta) + " degrees.");
    }
}
