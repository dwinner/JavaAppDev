// Непосредственное создание внутреннего класса в синтаксисе .new

public class DotNew
{
    public class Inner { }

    public static void main(String[] args)
    {
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }
}