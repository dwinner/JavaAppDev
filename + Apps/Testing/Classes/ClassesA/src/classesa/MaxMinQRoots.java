package classesa;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для определения наибольших/наименьших корней
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class MaxMinQRoots
{
    private ArrayList<Double> allRoots;
    private static final int INITIAL_CAPACITY = 64;
    
    {
        allRoots = new ArrayList<Double>(INITIAL_CAPACITY);
    }
    
    public MaxMinQRoots(QuadraticEquation[] qArray)
    {
        for (QuadraticEquation qEq : qArray)
        {
            allRoots.add(qEq.getRoot1());
            allRoots.add(qEq.getRoot2());
        }
    }
    
    public double getMinimumRoot()
    {
        return Collections.min(allRoots);
    }
    
    public double getMaximumRoot()
    {
        return Collections.max(allRoots);
    }
    
    @Deprecated public double[] getAllRoots()
    {
        double[] doubleRoots = new double[allRoots.size()];
        for (int i = 0; i < doubleRoots.length; i++)
        {
            doubleRoots[i] = allRoots.get(i);
        }
        return doubleRoots;
    }
    
}