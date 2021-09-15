/* ������������ ������������ �������� ���������� ������, ������� ����� ����
 �������� ������ �� ����� ������ ��� �������������� ����� �������. */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.util.logging.Logger;
/*
<applet code="MaxWD" width="340" height="260">
</applet>
*/
  
public class MaxWD extends Applet implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MaxWD.class.getName());
  
    private TextField   maxWDText,
                        orgPText,
                        periodText,
                        rateText,
                        numWDText;
    private Button doIt;
  
    private double principal; // ��������� ��������.
    private double rateOfRet; // ��������� ����� �������.
    private double numYears;  // ���� ������.
    private int numPerYear;   // ���������� ������ � ���.

    private NumberFormat nf;
 
    @Override public void init() {
        // ������������ ������������ GridBagLayout.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        Label heading = new Label("Maximum Regular Withdrawals");

        Label orgPLab = new Label("Original Principal");
        Label periodLab = new Label("Years");
        Label rateLab = new Label("Rate of Return");
        Label numWDLab = new Label("Number of Withdrawals per Year");
        Label maxWDLab = new Label("Maximum Withdrawal");

        maxWDText = new TextField(16);
        periodText = new TextField(16);
        orgPText = new TextField(16);
        rateText = new TextField(16);
        numWDText = new TextField(16);

        // ���� ��� ����������� �������� ������.
        maxWDText.setEditable(false);

        doIt = new Button("Compute");

        // ������ �����.
        gbc.weighty = 1.0; // ������������ ����������� 1

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);
 
        // ��������� ���������� � ������ ������� ����.
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(orgPLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(orgPText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(periodLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(periodText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(rateLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(rateText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(numWDLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(numWDText, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(maxWDLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(maxWDText, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(doIt, gbc);

        // �������� ��� ����������.
        add(heading);
        add(orgPLab);
        add(orgPText);
        add(periodLab);
        add(periodText);
        add(rateLab);
        add(rateText);
        add(numWDLab);
        add(numWDText);
        add(maxWDLab);
        add(maxWDText);
        add(doIt);

        // ���������������� ��� ������ ����������� � �������.
        orgPText.addActionListener(this);
        periodText.addActionListener(this);
        rateText.addActionListener(this);
        numWDText.addActionListener(this);
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

        String orgPStr = orgPText.getText();
        String periodStr = periodText.getText();
        String rateStr = rateText.getText();
        String numWDStr = numWDText.getText();

        try {
            if (orgPStr.length() != 0 && periodStr.length() != 0 &&
                rateStr.length() != 0 && numWDStr.length() != 0)
            {

                principal = Double.parseDouble(orgPStr);
                numYears = Double.parseDouble(periodStr);
                rateOfRet = Double.parseDouble(rateStr) / 100;
                numPerYear = Integer.parseInt(numWDStr);

                result = compute();

                maxWDText.setText(nf.format(result));
            }
            showStatus(""); // ������� ���������� ���������.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            maxWDText.setText("");
        }
    }
 
    // ���������� ������ ���������� ������.
    private double compute() {
        double b, e;
        double t1, t2;

        t1 = rateOfRet / numPerYear;

        b = (1 + t1);
        e = numPerYear * numYears;

        t2 = Math.pow(b, e) - 1;

        return principal * (t1/t2 + t1);
    }

}