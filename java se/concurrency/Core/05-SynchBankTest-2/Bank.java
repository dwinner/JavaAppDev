import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Банк с множеством счетов, использующий блокировки для сериализации доступа.
 * <p/>
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class Bank
{
    private final double[] accounts;

    /**
     * Конструирует банк.
     * <p/>
     * @param n              Количество счетов
     * @param initialBalance Начальный баланс каждого счета
     */
    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = initialBalance;
        }
    }

    /**
     * Переводит деньги с одного счета на другой.
     * <p/>
     * @param from   Счет, с которого выполняется перевод
     * @param to     Счет, на который выполняется перевод
     * @param amount Сумма перевода
     * @throws InterruptedException  
     */
    public synchronized void transfer(int from, int to, double amount) throws InterruptedException
    {
        while (accounts[from] < amount)
        {
            wait();
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f c %d na %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Common balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

    /**
     * Получает сумму всех счетов.
     * <p/>
     * @return Общий баланс.
     */
    private synchronized double getTotalBalance()
    {
        double sum = 0;
        for (double a : accounts)
        {
            sum += a;
        }
        return sum;
    }

    /**
     * Получает количество счетов в банке.
     * <p/>
     * @return Количество счетов
     */
    public int size()
    {
        return accounts.length;
    }
}
