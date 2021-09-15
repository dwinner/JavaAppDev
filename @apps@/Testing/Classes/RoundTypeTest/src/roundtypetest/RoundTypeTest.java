package roundtypetest;

/**
 * Тестирование типа, инкапсулирующего логику работы с кругом
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class RoundTypeTest
{
    private RoundTypeTest() {}

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args)
    {
        PointType center = new PointType(0, 0);
        PointType test = new PointType(2, 2);
        double rad = 5.0;
        CircleType rounder = new RoundType(center, rad);
        if (rounder instanceof RoundType)
        {
            if(((RoundType) rounder).contains(test))
            {
                System.out.println(rounder + " contains " + test);
            }
        }
    }

}