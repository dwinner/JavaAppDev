package accountmanager;

import java.util.Observable;

/**
 * Класс Observable, который представляет банковский счет с возможностью
 * начисления или снятия денег.
 * @author dwinner@inbox.ru
 */
public class Account extends Observable {
    
    // баланс счета
    private double balance;
    
    // имя счета только для чтения
    private String name;
    
    // Конструктор Account
    public Account(String accountName, double openingDeposit) {
        name = accountName;
        setBalance(openingDeposit);
    }

    // баланс счета и уведомление наблюдателей об изменении
    private void setBalance(double accountBalance) {
        balance = accountBalance;
        
        // Следует вызвать setChanged до notifyObservers,
        // чтобы указать об изменении модели
        setChanged();
        
        // Уведомление наблюдателей об изменении модели
        notifyObservers();
    }
    
    // Получение баланса счета
    public double getBalance() {
        return balance;
    }
    
    // Снятие суммы со счета
    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw negative amount");
        }
        
        // Обновление баланса счета
        setBalance(getBalance() - amount);
    }
    
    // Начисление суммы на счет
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative amount");
        }
        
        // Обновление баланса счета
        setBalance(getBalance() + amount);
    }
    
    // Получение имени счета
    @Override
    public String toString() {
        return name;
    }
    
}