// Вложение класса в тело метода.
interface Destination
{
    String readLabel();
}

public class Parcel5
{
    public Destination dest(String s)
    {
        class PDestination implements Destination
        {
            private String label;

            private PDestination(String whereTo) { label = whereTo; }

            public String readLabel() { return label; }
        }

        return new PDestination(s);
    }

    public static void main(String[] args)
    {
        Parcel5 p = new Parcel5();
        Destination d = p.dest("Tasmania");
    }
}