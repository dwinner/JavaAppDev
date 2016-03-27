enum Dean
{
    MMF("Bender"),
    FPMI("Bugaga"),
    GEO("AssAlled");
    private String name;
    Dean(String arg) { name = arg; }
    String getName() { return name; }
}

public class DeanDemo
{
    public static void main(String[] args)
    {
        Dean dn = Dean.valueOf("FPMI");
        System.out.print(dn.ordinal());
        System.out.print(" : " + dn + " : " + dn.getName());
    }
}