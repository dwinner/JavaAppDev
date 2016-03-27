// Перехват иерархий исключений

class Annoyance extends Exception {}
class Sneeze extends Annoyance {}

public class Human
{
    public static void main(String[] args)
    {
        // Перехват точного типа
        try { throw new Sneeze(); }
        catch (Sneeze s) { System.out.println("Catched Sneeze"); }
        catch (Annoyance a) { System.out.println("Catched Annoyance"); }
        
        // Перехват базового типа
        try { throw new Sneeze(); }
        catch (Annoyance a) { System.out.println("Catched Annoyance"); }
    }
}