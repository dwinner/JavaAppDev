/**
 * Банк с можеством счетов.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class Bank
{
    private final double[] accounts;
    
    /**
     * Конструктор банка.
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
    }
    
    /**
     * Перевод денег с одного счета на другой.
     * @param from Счет, с которого осуществляется перевод
     * @param to Счет, на который осуществляется перевод
     * @param amount Сумма перевода
     */
    public void transfer(int from, int to, double amount)
    {
        if (accounts[from] < amount)
        {
            return;
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f c %d na %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Common balance: %10.2f%n", getTotalBalance());
    }

    /**
     * Получить суммарный баланс.
     * @return  Общий баланс
     */
    private double getTotalBalance()
    {
        double sum = 0;
        for (double a : accounts)
        {
            sum += a;
        }
        return sum;
    }
    
    /**
     * Получить количество счетов в банке.
     * @return Количество счетов
     */
    public int size()
    {
        return accounts.length;
    }
}
