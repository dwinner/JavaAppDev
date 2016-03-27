
/**
 * @version 1.10 2004-08-02
 * @author Cay Horstmann
 */
import java.util.*;

/**
 * Программа сортирует набор элементов, сравнивая их описание.
 */
public class TreeSetTest
{
    public static void main(String[] args)
    {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>()
        {
            @Override public int compare(Item a, Item b)
            {
                String descrA = a.getDescription();
                String descrB = b.getDescription();
                return descrA.compareTo(descrB);
            }
        });

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}

/**
 * Элемент с описанием и номером партии.
 */
class Item implements Comparable<Item>
{
    private String description;
    private int partNumber;
    
    /**
     * Конструирует элемент.
     *
     * @param aDescription Описание элемента
     * @param aPartNumber Номер детали элемента
     */
    public Item(String aDescription, int aPartNumber)
    {
        description = aDescription;
        partNumber = aPartNumber;
    }

    /**
     * Извлекает описание элемента.
     *
     * @return Описание
     */
    public String getDescription() { return description; }

    @Override public String toString()
    {
        return "[descripion=" + description
            + ", partNumber=" + partNumber + "]";
    }

    @Override public boolean equals(Object otherObject)
    {
        if (this == otherObject)
        {
            return true;
        }
        if (otherObject == null)
        {
            return false;
        }
        if (getClass() != otherObject.getClass())
        {
            return false;
        }
        Item other = (Item) otherObject;
        return description.equals(other.description) && partNumber == other.partNumber;
    }

    @Override public int hashCode()
    {
        return 13 * description.hashCode() + 17 * partNumber;
    }

    @Override public int compareTo(Item other)
    {
        return partNumber - other.partNumber;
    }
}
