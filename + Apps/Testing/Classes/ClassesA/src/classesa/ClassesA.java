package classesa;

import classesa.QuadraticEquation.AnyRootException;
import classesa.QuadraticEquation.NoRootException;

/**
 * Вычисление корней квадратного уравнения.
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class ClassesA
{
    private ClassesA() {}
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings({"CallToThreadDumpStack", "UseOfSystemOutOrSystemErr"})
    public static void main(String[] args)
    {
        try
        {
            // Создание массива объектов
            QuadraticEquation[] qArr = new QuadraticEquation[]
            {
                new QuadraticEquation(1, -2, 1),
                new QuadraticEquation(1, 4, -3),
                new QuadraticEquation(1, -2, -3),
                new QuadraticEquation(5, -7, -15),
                new QuadraticEquation(2, -9, -6)
            };
            
            // Находим наобольшее и наименьшее значение корня
            MaxMinQRoots allRoots = new MaxMinQRoots(qArr);
            System.out.println("Minimum root is:\t" + allRoots.getMinimumRoot());
            System.out.println("Maximum root is:\t" + allRoots.getMaximumRoot());
        }
        catch (NoRootException ex)
        {
            ex.printStackTrace();
        }
        catch (AnyRootException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}