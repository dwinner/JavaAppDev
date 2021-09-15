package roundtypetest;

/**
 * Тип, имитирующий логику работы круга.
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class RoundType extends CircleType
{

    public RoundType()
    {
    }

    public RoundType(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius);
    }

    public RoundType(double radius) 
    {
        super(radius);
    }

    public RoundType(PointType pCenter, double radius)
    {
        super(pCenter, radius);
    }
     
    /**
     * Определение, содержится ли точка pt в круге
     * @param pt Точка для проверки
     * @return true, если содержится, false в противном случае
     */
    public boolean contains(PointType pt)
    {
        double pxPt = pt.getPx();
        double pyPt = pt.getPy();
        double pxCenter = getpCenter().getPx();
        double pyCenter = getpCenter().getPy();
        double sqDistance = (pxPt-pxCenter)*(pxPt-pxCenter)+(pyPt-pyCenter)*(pyPt-pyCenter);
        
        return (sqDistance <= getRadius()*getRadius()) ? true : false;
    }
    
}