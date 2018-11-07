//: ApplesAndOrangesWithoutGenerics.java
// ������� ������ ������ � ����������� (���������� ������ ��������������).
// {ThrowsException}
import java.util.*;

class Apple
{
    private static long counter;
    private final long id = counter++;
    public long id() { return id; }
}

class Orange {}

public class ApplesAndOrangesWithoutGenerics
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
        // �� ������������ ���������� ������� Orange:
        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++)
            ((Apple)apples.get(i)).id();
        // ������ Orange �������������� ������ �� ����� ����������
    }
}
/* (Execute to see output) *///:~