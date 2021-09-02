// Класс Vector.
import java.util.*;

public class VectorDemo
{
    public static void main(String args[])
    {
        // Исходные размер 3, инкремент равен 2
        @SuppressWarnings("UseOfObsoleteCollectionType")
        Vector<Double> v = new Vector<>(3, 2);

        System.out.println("Source dimension: " + v.size());
        System.out.println("Source capacity: " + v.capacity());

        v.addElement(1.0);
        v.addElement(2.0);
        v.addElement(3.0);
        v.addElement(4.0);

        System.out.println("Capacity after 4-th adding: " + v.capacity());
        v.addElement(5.45);
        System.out.println("Current capacity: " + v.capacity());
        v.addElement(6.08);
        v.addElement(7.0);
        System.out.println("Current capacity: " + v.capacity());
        v.addElement(9.4);
        v.addElement(10.0);

        System.out.println("Current capacity: " + v.capacity());
        v.addElement(11.0);
        v.addElement(12.0);

        System.out.println("First element: " + v.firstElement());
        System.out.println("Last element: " + v.lastElement());

        if (v.contains(3.0))
        {
            System.out.println("Vector contains 3.");
        }

        // перечислить элементы вектора
        Enumeration<Double> vEnum = v.elements();

        System.out.println("\nElements of vector:");
        while (vEnum.hasMoreElements())
        {
            System.out.println(vEnum.nextElement() + " ");
        }
        System.out.println();
    }
}
