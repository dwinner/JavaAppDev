
import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonUI extends BasicButtonUI
{
    private static MyButtonUI instance = null;

    @Override
    public void installUI(JComponent c)
    {
        // ����������� ��������� ��������� UI, ������������� � Basic UI ������
        super.installUI(c);

        // ������������� �������� ��������� ��� JButton
        // ��� ���������� ���������� AbstractButton, ��� ��� � ��� ����
        // ���, ��� ��� ����������
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setFocusable(true);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

    }

    /**
     * "��������" ��� �� ����� � ���-�� ����������, ������������ ��� �������� ������ BasicButtonUI, ����� ������ ��
     * ��������� � �� ������ BasicButtonUI ������ ������������ UI-�������������.
     * @param c UI-���������
     * @return  UI-������������� ����������
     */
    public static ComponentUI createUI(JComponent c)
    {
        if (instance == null)
        {
            instance = new MyButtonUI();
        }
        return new MyButtonUI();
    }

    @Override
    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractButton button = (AbstractButton) c;
        ButtonModel buttonModel = button.getModel();

        // ������ ������ ����� ������������ �������������

        // ��� ������
        g2D.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(), new Color(200, 200, 200)));

        // ����������� ���������� ������ ������, ��� ��� ��������� �����,
        // ����� ������� ��� ����� ������������ �� �����
        g2D.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);

        // ����� ��� ������
        g2D.setPaint(Color.GRAY);

        // ����� �������, ��� ����� ���������� ������ �� 1px ������, ��� ������/������ ����������,
        // ����� ������ � ������ ���� ������ ������� �� ������� ���������� � �� ����� �����.
        // � ������� ��� �� ���������, ��� ��� ��������� �������/������ �������� ������������ ��� ����������
        g2D.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 6, 6);

        // ����� ��������� ��� ������� ������
        if (buttonModel.isPressed())
        {
            g2D.translate(1, 1);
        }

        // ��������� ������ � ������ �����������
        super.paint(g, c);
    }
}