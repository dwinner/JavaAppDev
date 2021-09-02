// ������������� ���� �������������� � ���������� ��������������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class FormattedTFDemo
{
    private NumberFormat cf;
    private DateFormat df;
    private JLabel jlab;
    private JFormattedTextField jftfSalary;
    private JFormattedTextField jftfDate;
    private JFormattedTextField jftfEmpID;
    private JButton jbtnShow;

    public FormattedTFDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JFormattedTextField");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 270);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        jlab = new JLabel();

        // �������� ���� �������������� ���������������� ������, ������������ ��� �����
        // ����������������� �������. ������ ��������� ���������� ������ �����.
        try
        {
            MaskFormatter mf = new MaskFormatter("##-###");
            jftfEmpID = new JFormattedTextField(mf);
        }
        catch (ParseException exc)
        {
            System.out.println("Invalid Format");
            return;
        }
        jftfEmpID.setColumns(15);
        jftfEmpID.setValue("24-895");

        // �������� ���� �������������� ���������������� ������, ������������ ��� ������ �
        // ���������� �����������. ������ ��������� ���������� ������ �������� ������.
        cf = NumberFormat.getCurrencyInstance();
        cf.setMaximumIntegerDigits(5);
        cf.setMaximumFractionDigits(2);
        jftfSalary = new JFormattedTextField(cf);
        jftfSalary.setColumns(15);
        jftfSalary.setValue(new Integer(7000));

        // �������� ���� �������������� ���������������� �����, ������������ ��� ����� ����.
        df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        jftfDate = new JFormattedTextField(df);
        jftfDate.setColumns(15);
        jftfDate.setValue(new Date()); // ������������� ������� �����.

        // ���������� ����������� ��������� ������� � ����� ����� ������������������ ������.
        jftfEmpID.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // ����� �������� ���������� ��� ��������� �������� ����������.
                jlab.setText("Employee ID changed.");
            }
        });

        // ���������� ����������� ��������� ������� � ����� ��� ������ � ����������� � ��������.
        jftfSalary.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // ����� �������� ���������� ��� ��������� �������� ����������.
                jlab.setText("Monthly salary changed.");
            }
        });

        // ���������� ����������� ��������� ������� � ����� ��� ������ � ������.
        jftfDate.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // ����� �������� ���������� ��� ��������� �������� ����������.
                jlab.setText("Date hired changed.");
            }
        });

        // �������� ������ Show Updates.
        jbtnShow = new JButton("Show Updates");

        // ���������� ����������� ������� � ������� Show Updates.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // ����������� ��������������� ������. ��������, ��� ���������� � ����������
                // ����� � ���� ������������� � ������� ��� �� ��������, ������� ������������ �
                // ��������������� �����. ����������������� ����� ���������� ��� �������������.
                jlab.setText("<html>Employee ID: "
                    + jftfEmpID.getValue()
                    + "<br />Monthly Salary: "
                    + cf.format(jftfSalary.getValue())
                    + "<br />Date Hired: "
                    + df.format(jftfDate.getValue()));
            }
        });

        // ��������� ����������� � ������ ������ �����������.
        jfrm.getContentPane().add(new JLabel("Employee ID"));
        jfrm.getContentPane().add(jftfEmpID);
        jfrm.getContentPane().add(new JLabel("Monthly Salary"));
        jfrm.getContentPane().add(jftfSalary);
        jfrm.getContentPane().add(new JLabel("Date Hired"));
        jfrm.getContentPane().add(jftfDate);
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new FormattedTFDemo();
            }
        });
    }
}