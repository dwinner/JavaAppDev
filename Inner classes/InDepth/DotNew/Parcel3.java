// »спользование .new дл€ создани€ экземпл€ров внутренних классов

public class Parcel3
{
    class Contents
    {
        private int i = 11;

        public int value() { return i; }
    }

    class Destination
    {
        private String label;

        Destination(String whereTo) { label = whereTo; }

        String readLabel() { return label; }
    }

    public static void main(String[] args)
    {
        Parcel3 p = new Parcel3();
        // ƒл€ создани€ экземпл€ра внутреннего класса
        // необходимо использовать экземпл€р внешнего класса:
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tanzania");
    }
}