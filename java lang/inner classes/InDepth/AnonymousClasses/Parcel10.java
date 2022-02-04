// Демонстрация "инициализации экземпляра" для
// конструирования безымянного внутреннего класса.

interface Destination
{
    String readLabel();
}

public class Parcel10
{
    public Destination destination(final String dest, final float price)
    {
        return new Destination()
        {
            private int cost;
            private String label = dest;

            // Инициализация экземпляра для каждого объекта.
            {
                cost = Math.round(price);
                if (cost > 100)
                {
                    System.out.println("Under limit!");
                }
            }

            public String readLabel() { return label; }
        };
    }

    public static void main(String[] args)
    {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
    }
} // Output: Under limit!