//: initialization/TerminationCondition.java
// Т.н. "Условие готовности":
// Использование finalize() для выявления объекта,
// не осуществившего необходимой финализации.

class Book
{
    boolean checkedOut = false;

    Book(boolean checkOut) { checkedOut = checkOut; }

    void checkIn() { checkedOut = false; }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable
    {
        super.finalize();
        if (checkedOut)
        {
            System.out.println("Error: checked out");
        }
        // Обычно это делается так:
        // super.finalize(); // Вызов версии базового класса
    }
}

public class TerminationCondition
{
    public static void main(String[] args)
    {
        Book novel = new Book(true);
        // Правильная очистка:
        novel.checkIn();
        // Теряем ссылку, забыли про очистку
        new Book(true);
        // Принудительная сборка мусора и финализация
        System.gc();
    }
}
/* Output:
Error: checked out
 *///:~