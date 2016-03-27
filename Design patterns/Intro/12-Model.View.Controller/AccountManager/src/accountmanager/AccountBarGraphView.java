package accountmanager;

import java.awt.*;

/**
 * Подкласс класса AbstractAccountView, который отображает баланс счета в виде
 * столбчатой диаграммы.
 * @author dwinner@inbox.ru
 */
public class AccountBarGraphView extends AbstractAccountView {
    
    private static final long serialVersionUID = 1L;
    
    // Конструктор AccountBarGraphView
    public AccountBarGraphView(Account account) {
        super(account);
    }
    
    // Отображение баланса счета в виде столбчатой диаграммы
    @Override
    public void paintComponent(Graphics g) {
        // Обеспечение нужной последовательности отображения
        super.paintComponent(g);
        
        // Получение баланса счета
        double balance = getAccount().getBalance();
        
        // Вычисление высоты диаграммы (диаграмма имеет 200 пикселей в ширину
        // и представляет балансы счетов в пределах от -$5,000.00 до +$5,000.00)
        int barLength = (int) ((balance / 10000.0) * 200);
        
        // Если баланс положительный, он отображается черным
        if (balance >= 0.0) {
            g.setColor(Color.black);
            g.fillRect(105, 15, barLength, 20);
        }
        else {  // Если баланс отрицательный, он отображается красным
            g.setColor(Color.red);
            g.fillRect(105 + barLength, 15, -barLength, 20);
        }
        
        // Рисование вертикальных и горизонтальных осей
        g.setColor(Color.black);
        g.drawLine(5, 25, 205, 25);
        g.drawLine(105, 5, 105, 45);
        
        // Отображение надписей
        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.drawString("-$5,000", 5, 10);
        g.drawString("$0", 110, 10);
        g.drawString("+$5,000", 166, 10);
    }

    // Перерисовка диаграммы при обновлении изображения
    @Override
    protected void updateDisplay() {
        repaint();
    }
    
    // Получение предпочтительного размера диаграммы
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(210, 50);
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