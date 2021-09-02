// ��������� ������� ������ ���������

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CustomSBDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
    private JLabel jlabVSBInfo;
    private JLabel jlabHSBInfo;
    private JScrollBar jsbVert;
    private JScrollBar jsbHoriz;

    public CustomSBDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Scroll Bars Properties");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(260, 500);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �����, ������������ ������� �������� ����� ���������.
        jlabVert = new JLabel("Value of vertical scroll bar: 0");
        jlabHoriz = new JLabel("Value of horizontal scroll bar: 250");

        // ��������� ����������, ���������� � ��������� �������� ����� ���������.
        jsbVert = new JScrollBar(
                JScrollBar.VERTICAL,
                0,  // ��������� ��������.
                5,  // ����������.
                0,  // ����������� ��������.
                500 // ������������ ��������.
        );
        jsbHoriz = new JScrollBar(
                Adjustable.HORIZONTAL,
                250,    // ��������� ��������.
                0,      // ����������
                0,      // ����������� ��������.
                500     // ������������ ��������.
        );

        // ��������� �������� ����� ���������.

        // ������������ ������ ��������� ���� �����������.
        jsbVert.setPreferredSize(new Dimension(30, 200));
        // �������������� ������ ��������� ��� �����������
        jsbHoriz.setPreferredSize(new Dimension(200, 10));

        // ��������� ���������� ����� ��� �������������� ������ ���������.
        jsbHoriz.setBlockIncrement(25);

        // ����������� ������� ����������� ��� ����� ���������.

        // ����� ��� ��� ��������� ��������, ��������������� �������, ������������
        // ������ ��������� ������� ��������� �������� ������������.
        jsbVert.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // ���� ������ ��������� ������������ ���������, ������� �������� �� �����������.
                if (jsbVert.getValueIsAdjusting())
                {
                    return;
                }
                // ����������� ������ ��������.
                jlabVert.setText("Value of vertical scroll bar: " + ae.getValue());
            }
        });

        // �������������� ������ ��������� ��������� �� ��� ������� �����������, ����������
        // �� ����, ��������� �� ������������ ��������, ���������� ��������� ����������.
        jsbHoriz.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // ����������� ������ ��������.
                jlabHoriz.setText("Value of horizontal scroll bar: " + ae.getValue());
            }
        });

        // ����������� ������� ����� ���������.
        jlabVSBInfo = new JLabel("<html>Vertical Scroll Bar:<br />"
                + "Minimum value: "
                + jsbVert.getMinimum() + "<br />"
                + "Maximum value: "
                + jsbVert.getMaximum() + "<br />"
                + "Visible amount (extent): "
                + jsbVert.getVisibleAmount() + "<br />"
                + "Block increment: "
                + jsbVert.getBlockIncrement() + "<br />"
                + "Unit increment: "
                + jsbVert.getUnitIncrement());

        jlabHSBInfo = new JLabel("<html>Horizontal Scroll Bar:<br />"
                + "Minimum value: "
                + jsbHoriz.getMinimum() + "<br />"
                + "Maximum value: "
                + jsbHoriz.getMaximum() + "<br />"
                + "Visible amount (extent): "
                + jsbHoriz.getVisibleAmount() + "<br />"
                + "Block increment: "
                + jsbHoriz.getBlockIncrement() + "<br />"
                + "Unit increment: "
                + jsbHoriz.getUnitIncrement());

        // ���������� ����������� � ������ �����������.
        jfrm.getContentPane().add(jsbVert);
        jfrm.getContentPane().add(jsbHoriz);
        jfrm.getContentPane().add(jlabVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVSBInfo);
        jfrm.getContentPane().add(jlabHSBInfo);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new CustomSBDemo();
            }
        });
    }
}