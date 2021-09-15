// ������� ������ ��� ������� ������ �� �����.
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

    private double principal; // ��������� ������.
    private double intRate;   // ������ ��������.
    private double numYears;  // ���������� ���.

    // ���������� �������� �� ���. ��� �������� ����� ��������� ������� ������������.
    public final int payPerYear = 12;

    private NumberFormat nf;
 
    @Override public void init() {
        // ���������� ������������ GridBagLayout.
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
 
        // ���� Payment ������ ��� ������.
        paymentText.setEditable(false);
 
        doIt = new Button("Compute");
 
        // ������ ������� �����.
        gbc.weighty = 1.0; // ������������ ����������� 1.
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);
 
        // ��������� ����������� ����������� � ������ �������.
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
 
        // �������� ��� ����������.
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
  
        // ���������������� ��� ������ ����������� � ��������
        amountText.addActionListener(this);
        periodText.addActionListener(this);
        rateText.addActionListener(this);
        doIt.addActionListener(this);

        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
    }
  
    // ������������ �������� ������� Enter ��� �������� �������� ����
    // ��� ���������� ������ �� ������ Compute.
    public void actionPerformed(ActionEvent ae) {
        repaint();  
    }
 
    // ���������� ���������, ���� ��� ���� ���������.
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
            showStatus(""); // ������� ���������� ��������� �� �������.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            paymentText.setText("");
        }
    }
 
    // ���������� ������ ������� �� �����.
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