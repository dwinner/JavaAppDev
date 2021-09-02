//: CollectionSequence.java
import typeinfo.pets.*;
import java.util.*;

class InterfaceVsIterator
{
    private InterfaceVsIterator() { }

    public static void display(Iterator<Pet> it)
    {
        while (it.hasNext())
        {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }
  
    public static void display(Collection<Pet> pets)
    {
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
        System.out.println();
    }
}

public class CollectionSequence extends AbstractCollection<Pet>
{
    private Pet[] pets = Pets.createArray(8);
  
    public int size() { return pets.length; }
  
    public Iterator<Pet> iterator()
    {
        return new Iterator<Pet>()
        {
            private int index = 0;
            
            public boolean hasNext()
            {
                return index < pets.length;
            }
      
            public Pet next()
            {
                return pets[index++];
            }
      
            public void remove()
            { // Не реализован
                throw new UnsupportedOperationException();
            }
        };
    }
  
    public static void main(String[] args)
    {
        CollectionSequence c = new CollectionSequence();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
*///:~
