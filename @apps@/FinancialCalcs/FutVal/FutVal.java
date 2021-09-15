// Расчет будущей стоимости инвестиций.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.util.logging.Logger;
/*
<applet code="FutVal" width=340 height=240>
</applet>
*/
  
public class FutVal extends Applet implements ActionListener {
    
    private static final Logger LOG = Logger.getLogger(FutVal.class.getName());
    private static final long serialVersionUID = 1L;
  
    private TextField   amountText,
                        futvalText,
                        periodText,
                        rateText,
                        compText;
    private Button doIt;
  
    private double principal; // Начальный капитал.
    private double rateOfRet; // Норма прибыли.
    private double numYears;  // Срок инвестиций в годах.
    private int compPerYear;  // Число периодов расчета сложного процента.
 
    private NumberFormat nf;
 
    @Override public void init() {
        // Используем GridBagLayout.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);
 
        Label heading = new Label("Future Value of an Investment");
  
        Label amountLab = new Label("Principal");
        Label periodLab = new Label("Years");
        Label rateLab = new Label("Rate of Return");
        Label futvalLab = new Label("Future Value of Investment");
        Label compLab = new Label("Compounding Periods per Year ");
 
        amountText = new TextField(16);
        periodText = new TextField(16);
        futvalText = new TextField(16);
        rateText = new TextField(16);
        compText = new TextField(16);

        // Поле для отображения будущей стоимости инвестиций.
        futvalText.setEditable(false);

        doIt = new Button("Compute");
 
        // Задать менеджер расположения.
        gbc.weighty = 1.0; // Использовать коэффициент 1 
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);

        // Привязка компонентов к правой стороне.
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
        gbag.setConstraints(compLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(compText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(futvalLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(futvalText, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(doIt, gbc);

        add(heading);
        add(amountLab);
        add(amountText);
        add(periodLab);
        add(periodText);
        add(rateLab);
        add(rateText);
        add(compLab);
        add(compText);
        add(futvalLab);
        add(futvalText);
        add(doIt);

        // Регистрация для приема уведомлений о событиях.
        amountText.addActionListener(this);
        periodText.addActionListener(this);
        rateText.addActionListener(this);
        compText.addActionListener(this);
        doIt.addActionListener(this);

        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
    }
  
    // Нажатие клавиши Enter при активном текстовом поле или
    // щелчок на кнопке Compute.
    public void actionPerformed(ActionEvent ae) {
        repaint();  
    }
  
    @Override public void paint(Graphics g) {
        double result = 0.0;
 
        String amountStr = amountText.getText().trim();
        String periodStr = periodText.getText().trim();
        String rateStr = rateText.getText().trim();
        String compStr = compText.getText().trim();
 
        try {
            if (amountStr.length() != 0 && periodStr.length() != 0 &&
                rateStr.length() != 0 && compStr.length() != 0)
            {
                principal = Double.parseDouble(amountStr);
                numYears = Double.parseDouble(periodStr);
                rateOfRet = Double.parseDouble(rateStr) / 100;
                compPerYear = Integer.parseInt(compStr);

                result = compute();

                futvalText.setText(nf.format(result));
            }
            showStatus(""); // Удалить предыдущие сообщения.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            futvalText.setText("");
        }
    }
 
    // Подсчитать будущее значение.
    private double compute() {
        double b, e;

        b = (1 + rateOfRet/compPerYear);
        e = compPerYear * numYears;

        return principal * Math.pow(b, e);
    }

}