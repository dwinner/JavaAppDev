// ����������� ����������� ������� �� �����.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
/*
<applet code="RemBal" width="340" height="260">
</applet>
*/

public class RemBal extends Applet implements ActionListener {
  
    private TextField   orgPText,
                        paymentText,
                        remBalText,
                        rateText,
                        numPayText;
    private Button doIt;
  
    private double orgPrincipal; // ��������� �������.
    private double intRate;      // ������ ��������.
    private double payment;      // ������ ������� �������.
    private double numPayments;  // ���������� ��������� ��������.
 
    // ���������� �������� �� ���. ��� �������� ����� ���������
    // ������� ������������.
    public final int payPerYear = 12; 
 
    private NumberFormat nf;

    @Override public void init() {
        // ������������ ������������ GridBagLayout.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        Label heading = new Label("Find Loan Balance ");
  
        Label orgPLab = new Label("Original Principal");
        Label paymentLab = new Label("Amount of Payment");
        Label numPayLab = new Label("Number of Payments Made");
        Label rateLab = new Label("Interest Rate");
        Label remBalLab = new Label("Remaining Balance");

        orgPText = new TextField(16);
        paymentText = new TextField(16);
        remBalText = new TextField(16);
        rateText = new TextField(16);
        numPayText = new TextField(16);
 
        // ���� ��� ����������� ��������.
        remBalText.setEditable(false);

        doIt = new Button("Compute");

        // ������ �����.

        gbc.weighty = 1.0; // ����������� ��������� ������������ ����� ������������ �� ���������.

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);

        // �������� ����������� � ������ �������.
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(orgPLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(orgPText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(paymentLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(paymentText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(rateLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(rateText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(numPayLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(numPayText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(remBalLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(remBalText, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(doIt, gbc);

        // �������� ��� ����������.
        add(heading);
        add(orgPLab);
        add(orgPText);
        add(paymentLab);
        add(paymentText);
        add(numPayLab);
        add(numPayText);
        add(rateLab);
        add(rateText);
        add(remBalLab);
        add(remBalText);
        add(doIt);

        // ���������������� ��� ������ ����������� � ��������.
        orgPText.addActionListener(this);
        numPayText.addActionListener(this);
        rateText.addActionListener(this);
        paymentText.addActionListener(this);
        doIt.addActionListener(this);

        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
    }
  
    // ������������ ����� ������� Enter ��� �������� ��������� ����
    // ��� ������� �� ������ Compute.
    public void actionPerformed(ActionEvent ae) {
        repaint();  
    }
  
    @Override public void paint(Graphics g) {
        double result = 0.0;

        String orgPStr = orgPText.getText().trim();
        String numPayStr = numPayText.getText().trim();
        String rateStr = rateText.getText().trim();
        String payStr = paymentText.getText().trim();

        try {
            if (orgPStr.length() != 0 && numPayStr.length() != 0 &&
                rateStr.length() != 0 && payStr.length() != 0)
            {
                orgPrincipal = Double.parseDouble(orgPStr);
                numPayments = Double.parseDouble(numPayStr);
                intRate = Double.parseDouble(rateStr) / 100;
                payment = Double.parseDouble(payStr);

                result = compute();

                remBalText.setText(nf.format(result));
            }
            showStatus(""); // ������� ���������� ���������.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            remBalText.setText("");
        }
    }
 
    // ���������� ���������� ������ �� �����.
    private double compute() {
        double bal = orgPrincipal;
        double rate = intRate / payPerYear;

        for (int i = 0; i < numPayments; i++) {
            bal -= payment - (bal * rate);
        }

        return bal;
    }
}