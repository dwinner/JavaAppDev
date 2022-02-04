// Метод возвращает экземпляр безымянного внутреннего класса
public class Parcel7
{
    public Contents contents()
    {
        return new Contents()
        {	// Вставить определение класса?!
            private int i = 11;

            public int value() { return i; }
        };
    }

    public static void main(String[] args)
    {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}

// Расширенная версия
public class Parcel7b
{
    class MyContents implements Contents
    {
        private int i = 11;

        public int value() { return i; }
    }

    public Contents contents() { return new MyContents(); }

    public static void main(String[] args)
    {
        Parcel7b p = new Parcel7b();
        Contents c = p.contents();
    }
}