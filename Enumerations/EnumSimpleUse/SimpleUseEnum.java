import static java.lang.System.*;

enum Faculty
{
    MMF,
    FPMI,
    GEO
}

public class SimpleUseEnum
{
    public static void main(String[] args)
    {
        Faculty current;
        current = Faculty.GEO;
        switch (current)
        {
            case GEO:
                out.print(current);
                break;
            case MMF:
                out.print(current);
                break;
            default:
                out.print("Out case: " + current);
        }
    }
}