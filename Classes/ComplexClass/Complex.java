// Проектирование класса Complex
public class Complex
{
    private static final double EPS = 1e-12;	// Точность вычислений
    private double re;	// действительная часть
    private double im;	// мнимая часть

    // Конструкторы класса
    public Complex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }

    public Complex(double re) { this(re, 0.0); }

    public Complex() { this(0.0, 0.0); }

    public Complex(Complex z) { this(z.re, z.im); }

    // -- Методы доступа -----------------------------------------------------------------
    public double getRe() { return re; }

    public double getIm() { return im; }

    public Complex getZ() { return new Complex(re, im); }

    // -----------------------------------------------------------------------------------
    // Модуль комплексного числа
    public double mod() { return Math.sqrt(re * re + im * im); }

    // Аргумент комплексного числа
    public double arg() { return Math.atan2(re, im); }

    // Проверка, является ли число действительным
    public boolean isReal() { return (Math.abs(im) < EPS); }

    // Вывод числа на консоль
    public void pr()
    {
        System.out.println(re + (im < 0.0 ? "" : "+") + im + "i");
    }

    // -- Переопределение методов класса Object ------------------------------------------
    @Override public boolean equals(Object z)
    {
        if (z instanceof Complex)
        {
            z = (Complex) z;
        }
        else
        {
            return super.equals(z);
        }
        return ((Math.abs(re - ((Complex) z).re) < EPS)
            && (Math.abs(im - ((Complex) z).im) < EPS));
    }

    @Override public String toString()
    {
        return "Complex: " + re + " " + im;
    }

    // -----------------------------------------------------------------------------------
    // -- Методы, реализующие операции +=, -=, *=. /= ------------------------------------
    public void add(Complex z)
    {
        re += z.re;
        im += z.im;
    }

    public void sub(Complex z)
    {
        re -= z.re;
        im -= z.im;
    }

    public void mul(Complex z)
    {
        double t = re * z.re - im * z.im;
        im = re * z.im + im * z.re;
        re = t;
    }

    public void div(Complex z)
    {
        double m = z.mod();
        double t = re * z.re - im * z.im;
        im = (im * z.re - re * z.im) / m;
        re = t / m;
    }

    // -----------------------------------------------------------------------------------
    // -- Методы, реализующие операции +, -, *, / ----------------------------------------
    public Complex plus(Complex z)
    {
        return new Complex(re + z.re, im + z.im);
    }

    public Complex minus(Complex z)
    {
        return new Complex(re - z.re, im - z.im);
    }

    public Complex asterisk(Complex z)
    {
        return new Complex(re * z.re - im * z.im, re * z.im + im * z.re);
    }

    public Complex slash(Complex z)
    {
        double m = z.mod();
        return new Complex((re * z.re - im * z.im) / m, (im * z.re - re * z.im) / m);
    }
}
