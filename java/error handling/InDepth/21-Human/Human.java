// �������� �������� ����������

class Annoyance extends Exception {}
class Sneeze extends Annoyance {}

public class Human
{
    public static void main(String[] args)
    {
        // �������� ������� ����
        try { throw new Sneeze(); }
        catch (Sneeze s) { System.out.println("Catched Sneeze"); }
        catch (Annoyance a) { System.out.println("Catched Annoyance"); }
        
        // �������� �������� ����
        try { throw new Sneeze(); }
        catch (Annoyance a) { System.out.println("Catched Annoyance"); }
    }
}