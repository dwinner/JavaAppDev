public abstract class Operation
{
    public abstract void sort(int mass[]);
}

public class SortByMax extends Operation
{
    public void sort(int mass[])
    {
        for (int i = 0; i < mass.length; ++i)
        {
            for (int j = i; j < mass.length; ++j)
            {
                if (mass[j] > mass[i])
                {
                    int m = mass[i];
                    mass[i] = mass[j];
                    mass[j] = m;
                }
            }
        }
        System.out.print("SortByMax : ");
        for (int i : mass)
            System.out.print(i + " ");
        System.out.print('\n');
    }
}

public class SortByMin extends Operation
{
    public void sort(int mass[])
    {
        for (int i = 0; i < mass.length; ++i)
        {
            for (int j = i; j < mass.length; ++j)
            {
                if (mass[j] < mass[i])
                {
                    int m = mass[i];
                    mass[i] = mass[j];
                    mass[j] = m;
                }
            }
        }
        System.out.print("SortByMin : ");
        for (int i : mass)
            System.out.print(i + " ");
        System.out.println('\n');
    }
}

public class Sorting
{
    private Operation operation = null;
    
    public Sorting(int operation)
    {
        switch (operation)
        {
            case 1:
                this.operation = new SortByMax();
                break;
            case 2:
                this.operation = new SortByMin();
                break;
            default:
                System.out.println("Fail operation");
        }
    }
    
    public void sorting(int[] mass)
    {
        if (operation != null)
            operation.sort(mass);
        else
            return;
    }
}

public class MainClass
{
    public static void main(String args[])
    {
        int mass[] = {28, 9, 71, 8, 35, 5, 51};
        Sorting cont1 = new Sorting(1);
        Sorting cont2 = new Sorting(2);
        cont1.sorting(mass);
        cont2.sorting(mass);
    }
}