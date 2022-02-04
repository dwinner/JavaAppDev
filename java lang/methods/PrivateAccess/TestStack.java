// Инкапсулированный доступ.
class Stack
{
    /* Теперь как stck, так и tos есть private. Это значит,
    что они не могут быть случайно или намеренно
    изменены опасным для стека способом. */

    private int stck[] = new int[10];
    private int tos;

    // Инициализировать вершину стека
    Stack()
    {
        tos = -1;
    }

    // Поместить элемент в стек
    void push(int item)
    {
        if (tos == 9)
        {
            System.out.println("Stack is full.");
        }
        else
        {
            stck[++tos] = item;
        }
    }

    // Вытолкнуть элемент из стека
    int pop()
    {
        if (tos < 0)
        {
            System.out.println("Stack is empty.");
            return 0;
        }
        else
        {
            return stck[tos--];
        }
    }
}

public class TestStack
{
    public static void main(String args[])
    {
        Stack mystack1 = new Stack();
        Stack mystack2 = new Stack();

        // Поместить несколько чисел в стек
        for (int i = 0; i < 10; i++)
        {
            mystack1.push(i);
        }
        for (int i = 10; i < 20; i++)
        {
            mystack2.push(i);
        }

        // Вытолкнуть эти числа из стека
        System.out.println("Stack in mystack1:");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(mystack1.pop());
        }

        System.out.println("Stack in mystack2:");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(mystack2.pop());
        }

        // эти операторы не верны
        // mystack1.tos = -2;
        // mystack2.stck[3] = 100
    }
}
