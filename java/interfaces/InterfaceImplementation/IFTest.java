// Примеры реализации интерфейсов.
interface IntStack
{
    void push(int item);	// запомнить элемент
    int pop();			// извлечь элемент
}

// Реализация IntStack, которая использует фиксированную память.
class FixedStack implements IntStack
{
    private int stck[];
    private int tos;
  
    // Выделить память и инициализировать стек
    FixedStack(int size)
    {
        stck = new int[size];
        tos = -1;
    }
  
    // поместить элемент в стек
    public void push(int item)
    {
        if (tos == stck.length-1)	// использовать переменную length
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }
  
    // извлечь элемент из стека
    public int pop()
    {
        if (tos < 0)
        {
            System.out.println("Stack is empty.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

// Реализует "растущий" стек.
class DynStack implements IntStack
{  
    private int stck[];
    private int tos;
  
    // Выделить память и инициализировать стек
    DynStack(int size)
    {
        stck = new int[size];
        tos = -1;
    }
  
    // поместить элемент в стек
    public void push(int item)
    {
        // если стек заполнен, увеличить его размер
        if (tos == stck.length-1)
        {
            int temp[] = new int[stck.length * 2];	// двойной размер
            for (int i=0; i<stck.length; i++)
                temp[i] = stck[i];
            stck = temp;
            stck[++tos] = item;
        }
        else
            stck[++tos] = item;
    }
  
    // Извлечь элемент из стека
    public int pop()
    {
        if (tos < 0)
        {
            System.out.println("Stack is empty.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

// Создать интерфейсную переменную и обратиться к стекам через неё.
public class IFTest
{
    public static void main(String args[])
    {
        IntStack mystack;	// создать ссылочную переменную интерфейса
        DynStack ds = new DynStack(5);
        FixedStack fs = new FixedStack(8);
  	
        mystack = ds;	// загрузить динамический стек
        // поместить несколько чисел в стек
        for (int i=0; i<12; i++)
            mystack.push(i);
  	
        mystack = fs;	// Загрузить фиксированный стек
        for (int i=0; i<8; i++)
            mystack.push(i);
  	
        mystack = ds;
        System.out.println("Values of dynamic stack:");
        for (int i=0; i<12; i++)
            System.out.println(mystack.pop());
  	
        mystack = fs;
        System.out.println("Values of fixed stack:");
        for (int i=0; i<8; i++)
            System.out.println(mystack.pop());
    }
}
