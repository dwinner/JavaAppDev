// Простой апплет для расчета выплат по ссуде.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.util.logging.Logger;
/*
  <applet code="RegPay" width=280 height=200>
  </applet>
*/

public class RegPay extends Applet implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RegPay.class.getName());

    private TextField   amountText,
                        paymentText,
                        periodText,
                        rateText;
    private Button doIt;

    private double principal; // Начальный баланс.
    private double intRate;   // Ставка процента.
    private double numYears;  // Количество лет.

    // Количество платежей за год. Это значение можно разрешить вводить пользователю.
    public final int payPerYear = 12;

    private NumberFormat nf;
 
    @Override public void init() {
        // Используем расположение GridBagLayout.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);
 
        Label heading = new Label("Compute Monthly Loan Payments");
  
        Label amountLab = new Label("Principal");
        Label periodLab = new Label("Years");
        Label rateLab = new Label("Interest Rate");
        Label paymentLab = new Label("Monthly Payments");
 
        amountText = new TextField(16);
        periodText = new TextField(16);
        paymentText = new TextField(16);
        rateText = new TextField(16);
 
        // Поле Payment только для чтения.
        paymentText.setEditable(false);
 
        doIt = new Button("Compute");
 
        // Задать размеры сетки.
        gbc.weighty = 1.0; // Использовать коэффициент 1.
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);
 
        // Привязать большинство компонентов к правой стороне.
        gbc.anchor = GridBagConstraints.EAST;
 
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(amountLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(amountText, gbc);
 
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(periodLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(periodText, gbc);
 
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(rateLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(rateText, gbc);
 
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(paymentLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(paymentText, gbc);
 
        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(doIt, gbc);
 
        // Добавить все компоненты.
        add(heading);
        add(amountLab);
        add(amountText);
        add(periodLab);
        add(periodText);
        add(rateLab);
        add(rateText);
        add(paymentLab);
        add(paymentText);
        add(doIt);
  
        // Зарегистрировать для приема уведомлений о событиях
        amountText.addActionListener(this);
        periodText.addActionListener(this);
        rateText.addActionListener(this);
        doIt.addActionListener(this);

        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
    }
  
    // Пользователь нажимает клавишу Enter при активном тектовом поле
    // или производит щелчок на кнопке Compute.
    public void actionPerformed(ActionEvent ae) {
        repaint();  
    }
 
    // Отобразить результат, если все поля заполнены.
    @Override public void paint(Graphics g) {
        double result = 0.0;

        String amountStr = amountText.getText();
        String periodStr = periodText.getText();
        String rateStr = rateText.getText();
 
        try {
            if (amountStr.length() != 0 && periodStr.length() != 0 && rateStr.length() != 0) {

                principal = Double.parseDouble(amountStr);
                numYears = Double.parseDouble(periodStr);
                intRate = Double.parseDouble(rateStr) / 100;
 
                result = compute();
  
                paymentText.setText(nf.format(result));
            }
            showStatus(""); // Удалить предыдущие сообщения об ошибках.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            paymentText.setText("");
        }
    }
 
    // Подсчитать размер платежа по ссуде.
    private double compute() {
        double numer;
        double denom;
        double b, e;

        numer = intRate * principal / payPerYear;
    
        e = -(payPerYear * numYears);
        b = (intRate / payPerYear) + 1.0;

        denom = 1.0 - Math.pow(b, e);

        return numer / denom;
    }
    
}