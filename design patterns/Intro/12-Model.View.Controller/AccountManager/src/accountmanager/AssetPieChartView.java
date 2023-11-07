package accountmanager;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.border.*;
import javax.swing.*;

/**
 * Подкласс класса AbstractAccountView, который отображает
 * балансы счетов с несколькими субсчетами в виде круговой диаграммы.
 * @author dwinner@inbox.ru
 */
public class AssetPieChartView extends JPanel implements Observer {
    
    private static final long serialVersionUID = 1L;
    
    // Задание наблюдаемых счетов
    private List<Account> accounts = new ArrayList<Account>();
    
    // Соответствие цветов для отображения секторов диаграммы
    private Map<Account,Color> colors = new HashMap<Account,Color>();
    
    /**
     * Добавление счета для представления в круговой диаграмме
     * @param account Счет
     * @throws NullPointerException
     */
    public void addAccount(Account account) throws NullPointerException {
        // Не допускать пустых (null) счетов
        if (account == null) {
            throw new NullPointerException();
        }
        
        // Добавление счета в вектор счетов
        accounts.add(account);
        // Добавление цвета в хэш-таблицу для отображения счета в виде сектора
        colors.put(account, getRandomColor());
        
        // Регистрация наблюдателя для получения обновления счета
        account.addObserver(this);
        
        // Обновление информации о счете
        repaint();
    }
    
    // Удаление счета из круговой диаграммы
    public void removeAccount(Account account) {
        // Прекратить получение обновлений для данного счета
        account.deleteObserver(this);
        
        // Удаление счета из вектора счетов
        accounts.remove(account);
        
        // Удаление цвета для счета из хэш-таблицы
        colors.remove(account);
        
        // Обновление изображения при удалении информации о счете
        repaint();
    }
    
    // Отображение балансов счетов в круговой диаграмме
    @Override
    public void paintComponent(Graphics g) {
        // Обеспечение нужной последовательности отображения
        super.paintComponent(g);
        
        // Отображение круговой диаграммы
        drawPieChart(g);
        
        // Отображение надписи, поясняющей назначение цветов
        drawLegend(g);
    }

    // Получение обновлений от наблюдаемого счета
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    // Получение случайного цвета для рисования сектора диаграммы
    private Color getRandomColor() {
        // Вычисление случайных значений для красного, зеленого и синего
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        
        // Возврат вновь созданного цвета
        return new Color(red, green, blue);
    }

    // Отображение круговой диаграммы
    private void drawPieChart(Graphics g) {
        // Получение объединенного баланса счета
        double totalBalance = getTotalBalance();
        
        // Создание временных переменных для вычислений
        double percentage = 0.0;
        int startAngle = 0;
        int arcAngle = 0;
        
        Iterator<Account> accountIterator = accounts.iterator();
        Account account = null;
        
        // Рисование секторов диаграммы для каждого счета
        while (accountIterator.hasNext()) {
            // Получение следующего счета от объекта Iterator
            account = accountIterator.next();
            
            // Рисование секторов только для включенных счетов
            if (!includeAccountInChart(account)) {
                continue;
            }
            
            // Получение доли счета от общего баланса
            percentage = account.getBalance() / totalBalance;
            
            // Вычисление угла дуги для счета
            arcAngle = (int) Math.round(percentage * 360);
            
            // Задание цвета для сектора диаграммы
            g.setColor(colors.get(account));
            
            // Отображение сектора диаграммы для счета
            g.fillArc(5, 5, 100, 100, startAngle, arcAngle);
            
            // Вычисление начального угла для следующего сектора диаграммы
            startAngle += arcAngle;
        }
    }

    // Отображение надписи для диаграммы
    private void drawLegend(Graphics g) {
        Iterator<Account> accountIterator = accounts.iterator();
        Account account = null;
        
        // Создание шрифта для имени диаграммы
        Font font = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(font);
        
        // Получение характеристик шрифта для расчета смещений и параметров
        // позиционирования для пояснений
        FontMetrics metrics = getFontMetrics(font);
        int ascent = metrics.getMaxAscent();
        int offsetY = ascent + 2;
        
        // Отображение пояснений для каждого счета
        for (int i = 1; accountIterator.hasNext(); i++) {
            // Получить следующий счет от Iterator
            account = accountIterator.next();
            
            // Отображение следующего счета со смещением
            g.setColor(colors.get(account));
            g.fillRect(125, offsetY * i, ascent, ascent);
            
            // Отображение имени счета
            g.setColor(Color.black);
            g.drawString(account.toString(), 140, offsetY * i + ascent);
        }
    }

    // Получение объединенного баланса для всех наблюдаемых счетов
    private double getTotalBalance() {
        double sum = 0.0;
        
        Iterator<Account> accountIterator = accounts.iterator();
        Account account = null;
        
        // Вычисление общего баланса
        while (accountIterator.hasNext()) {
            account = accountIterator.next();
            
            // Добавление к сумме только для включенных счетов
            if (includeAccountInChart(account)) {
                sum += account.getBalance();
            }
        }
        
        return sum;
    }

    // Возврат true, если данный счет следует включать в круговую диаграмму
    private boolean includeAccountInChart(Account account) {
        // Включение только активных счетов (с положительными балансами)
        return (account.getBalance() > 0.0);
    }
    
    // Получение предпочтительного размера диаграммы
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(210, 110);
    }
    
    // Получение минимального размера диаграммы
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
    // Получение максимального размера диаграммы
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
       
}