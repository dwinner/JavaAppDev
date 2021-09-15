// ������ ��������� ���������� ��� ��������� ����������� ����� � �������
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.logging.Logger;
import javax.swing.*;
/*
<applet code="InitInv" width="340" height="240">
</applet>
*/
  
public class InitInv extends JApplet implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(InitInv.class.getName());
  
    private JTextField  targetText,
                        initialText,
                        periodText,
                        rateText,
                        compText;
    private JButton doIt;
  
    private double targetValue; // ������� �� ������ �������.
    private double rateOfRet;   // ����� �������.
    private double numYears;    // ���� ���������� � �����.
    private int compPerYear;    // ���������� �������� �������� ��������.

    private NumberFormat nf;
 
    @Override public void init() {
        // ���������� �������� ������������ GridBagLayout.
        JPanel contentPane = new JPanel();
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.setLayout(gbag);
 
        JLabel heading = new JLabel("Initial Investment Needed for Future Value");
  
        JLabel targetLab = new JLabel("Desired Future Value ");
        JLabel periodLab = new JLabel("Years");
        JLabel rateLab = new JLabel("Rate of Return");
        JLabel compLab = new JLabel("Compounding Periods per Year");
        JLabel initialLab = new JLabel("Initial Investment Required");
 
        targetText = new JTextField(16);
        periodText = new JTextField(16);
        initialText = new JTextField(16);
        rateText = new JTextField(16);
        compText = new JTextField(16);

        // ���� ��� ����������� ���������� ��������.
        initialText.setEditable(false);

        doIt = new JButton("Compute");

        // ������ �����.
        gbc.weighty = 1.0; // ���������� ����������� 1
        gbc.weightx = 0.5;

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbag.setConstraints(heading, gbc);

        // �������� ����������� � ������ �������.
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(targetLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(targetText, gbc);

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
        gbag.setConstraints(initialLab, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(initialText, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbag.setConstraints(doIt, gbc);
 
        // �������� ��� ����������.
        contentPane.add(heading);
        contentPane.add(targetLab);
        contentPane.add(targetText);
        contentPane.add(periodLab);
        contentPane.add(periodText);
        contentPane.add(rateLab);
        contentPane.add(rateText);
        contentPane.add(compLab);
        contentPane.add(compText);
        contentPane.add(initialLab);
        contentPane.add(initialText);
        contentPane.add(doIt);

        // ���������������� ��� ������ ����������� � ��������.
        targetText.addActionListener(this);
        periodText.addActionListener(this);
        rateText.addActionListener(this);
        compText.addActionListener(this);
        doIt.addActionListener(this);

        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        
        setContentPane(new JScrollPane(contentPane));
        setVisible(true);
    }
  
    // ������� ������� Enter � �������� ��������� ���� ���
    // ������ �� ������ Compute.
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    @Override public void paint(Graphics g) {
        double result = 0.0;
 
        String targetStr = targetText.getText().trim();
        String periodStr = periodText.getText().trim();
        String rateStr = rateText.getText().trim();
        String compStr = compText.getText().trim();
 
        try {
            if (targetStr.length() != 0 && periodStr.length() != 0 &&
                rateStr.length() != 0 && compStr.length() != 0) 
            {

                targetValue = Double.parseDouble(targetStr);
                numYears = Double.parseDouble(periodStr);
                rateOfRet = Double.parseDouble(rateStr) / 100;
                compPerYear = Integer.parseInt(compStr);

                result = compute();

                initialText.setText(nf.format(result));
            }
            showStatus(""); // ������� ���������� ���������.
        }
        catch (NumberFormatException exc) {
            showStatus("Invalid Data");
            initialText.setText("");
        }
    }

    // ���������� ��������� ��������� ����������������.
    private double compute() {
        double b, e;

        b = (1 + rateOfRet/compPerYear);
        e = compPerYear * numYears;

        return targetValue / Math.pow(b, e);
    }
  
}