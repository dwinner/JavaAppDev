import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Банк с множеством счетов, использующий блокировки для сериализации доступа.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class Bank
{
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;
    
    /**
     * Конструирует банк.
     * @param n Количество счетов
     * @param initialBalance Начальный баланс каждого счета
     */
    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = initialBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }
    
    /**
     * Переводит деньги с одного счета на другой.
     * @param from Счет, с которого выполняется перевод
     * @param to Счет, на который выполняется перевод
     * @param amount Сумма перевода
     */
    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        bankLock.lock();
        try
        {
            while (accounts[from] < amount)
            {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f c %d na %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Common balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }
        finally
        {
            bankLock.unlock();
        }
    }

    /**
     * Получает сумму всех счетов.
     * @return Общий баланс. 
     */
    private double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double sum = 0;
            for (double a : accounts)
            {
                sum += a;
            }
            return sum;
        }
        finally
        {
            bankLock.unlock();
        }
    }
    
    /**
     * Получает количество счетов в банке.
     * @return Количество счетов
     */
    public int size()
    {
        return accounts.length;
    }
}
