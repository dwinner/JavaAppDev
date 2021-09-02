// Пример интерфейса.
interface Callback
{
    void callback(int param);
}

// Класс, реализующий Callback-интерфейс
class Client implements Callback
{
    // реализация Callback интерфейса
    public void callback(int p)
    {
        System.out.println("Callback is called with argument " + p);
    }
    void nonIfaceMeth()
    {
        System.out.println("Classes, relizing interfaces must be define other members");
    }
}

// Другая реализация Callback.
class AnotherClient implements Callback
{
    // Реализация Callback интерфейса
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
  	
        c = ob;	// c теперь ссылается на объект AnotherClient
        c.callback(42);
    }
}
