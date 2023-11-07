/**
 * Синхронизация доступа через реентерабельную блокировку и условия.
 * @author JavaFx
 */
public class SynchBankTest1
{
    private static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    
    public static void main(String[] args)
    {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++)
        {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
