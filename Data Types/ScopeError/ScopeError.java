// Ошибки переопределения имен переменных в блоке.
public class ScopeError
{
    public static void main(String args[])
    {
        int bar = 1;
        {	// создается новая область действия
            int bar = 2;	// ошибка времени выполнения - bar уже определена!
        }
    }
}
