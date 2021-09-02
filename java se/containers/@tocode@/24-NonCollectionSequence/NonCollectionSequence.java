//: NonCollectionSequence.java
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

class PetSequence { protected Pet[] pets = Pets.createArray(8); }

public class NonCollectionSequence extends PetSequence
{
    public Iterator<Pet> iterator()
    {
        return new Iterator<Pet>()
        {
            private int index = 0;
            
            public boolean hasNext() { return index < pets.length; }
      
            public Pet next() { return pets[index++]; }
      
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
  
    public static void main(String[] args)
    {
        NonCollectionSequence nc = new NonCollectionSequence();
        InterfaceVsIterator.display(nc.iterator());
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
*///:~
