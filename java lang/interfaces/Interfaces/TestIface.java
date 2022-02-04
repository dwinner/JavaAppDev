// ������ ����������.
interface Callback
{
    void callback(int param);
}

// �����, ����������� Callback-���������
class Client implements Callback
{
    // ���������� Callback ����������
    public void callback(int p)
    {
        System.out.println("Callback is called with argument " + p);
    }
    void nonIfaceMeth()
    {
        System.out.println("Classes, relizing interfaces must be define other members");
    }
}

// ������ ���������� Callback.
class AnotherClient implements Callback
{
    // ���������� Callback ����������
    public void callback(int p)
    {
        System.out.println("Other version of callback");
        System.out.println("p-square is " + (p*p));
    }
}

public class TestIface
{
    public static void main(String args[])
    {
        Callback c = new Client();
        AnotherClient ob = new AnotherClient();
  	
        c.callback(42);
  	
        c = ob;	// c ������ ��������� �� ������ AnotherClient
        c.callback(42);
    }
}
