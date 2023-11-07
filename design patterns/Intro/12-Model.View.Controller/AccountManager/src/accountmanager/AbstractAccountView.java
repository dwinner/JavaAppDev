package accountmanager;

import java.util.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Абстрактный класс, который представляет визуальное отображение счета Account.
 * @author dwinner@inbox.ru
 */
public abstract class AbstractAccountView extends JPanel implements Observer {
    
    // Наблюдаемый счет
    private Account account;
    
    // Конструктор AbstractAccountView
    @SuppressWarnings("LeakingThisInConstructor")
    public AbstractAccountView(Account observableAccount) throws NullPointerException {
        if (observableAccount == null) {    // Не разрешать пустые счета
            throw new NullPointerException();
        }
        
        // Обновление члена данных счета новым счетом
        account = observableAccount;
        
        // Регистрация наблюдателя Observer для получения изменений счета
        account.addObserver(this);
        
        // Задание свойств отображения
        setBackground(Color.white);
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
    }
    
    /**
     * Получение счета Account, для которого это представление
     * является наблюдателем
     * @return account
     */
    public Account getAccount() {
        return account;
    }
    
    // Обновление изображения сведениями о балансе счета
    protected abstract void updateDisplay();
    
    // Получение обновления от наблюдаемого счета
    @Override
    public void update(Observable observable, Object object) {
        updateDisplay();
    }
    
}