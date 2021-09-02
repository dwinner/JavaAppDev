//: initialization/TerminationCondition.java
// �.�. "������� ����������":
// ������������� finalize() ��� ��������� �������,
// �� �������������� ����������� �����������.

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
        // ������ ��� �������� ���:
        // super.finalize(); // ����� ������ �������� ������
    }
}

public class TerminationCondition
{
    public static void main(String[] args)
    {
        Book novel = new Book(true);
        // ���������� �������:
        novel.checkIn();
        // ������ ������, ������ ��� �������
        new Book(true);
        // �������������� ������ ������ � �����������
        System.gc();
    }
}
/* Output:
Error: checked out
 *///:~