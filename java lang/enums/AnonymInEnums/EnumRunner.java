enum Shape
{
    RECTANGLE,
    SQUARE,
    TRIANGLE
    {
        // Анинимный класс
        @Override public double getSquare()
        {
            // Версия для TRIANGLE
            return a*b/2;
        }
    };
    
    public double a, b;
    
    public void setShape(double a, double b)
    {
        if ((a <= 0 || b <= 0) || a != b && this == SQUARE)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.a = a;
            this.b = b;
        }
    }
    
    public double getSquare()
    {
        // Версия для RECTANGLE и SQUARE
        return a * b;
    }
    
    public String getParameters()
    {
        return "a=" + a + ", b=" + b;
    }
}

public class EnumRunner
{
    private EnumRunner() { }
    
    public static void main(String[] args)
    {
        int i = 4;
        for (Shape f : Shape.values())
        {
            f.setShape(3, i--);
            System.out.println(f.name() + " -> " + f.getParameters() +
                    " Square = " + f.getSquare());
        }
    }

}