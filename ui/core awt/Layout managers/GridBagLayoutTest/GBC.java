
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * ���� ����� �������� ������ � ������� GridBagConstraints.
 * <p/>
 * @version 1.01 2004-05-06
 * @author Cay Horstmann
 */
public class GBC extends GridBagConstraints
{
    /**
     * ������� ������ GBC, ��������� gridx, gridy. �������� ��������� ���������� ����������� ��
     * ���������.
     * <p/>
     * @param gridx ������� gridx
     * @param gridy ������� gridy
     */
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * ������� ������ GBC, ��������� gridx, gridy, gridwidth � gridheight. �������� ���������
     * ���������� ����������� �� ���������.
     * <p/>
     * @param gridx      ������� gridx
     * @param gridy      ������� gridy
     * @param gridwidth  ���������� ������ � ����������� x
     * @param gridheight ���������� ������ � ����������� y
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * ������������� �������� anchor.
     * <p/>
     * @param anchor �������� ���������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     * ������������� �������� fill.
     * <p/>
     * @param fill �������� ���������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     * ������������� ���� ������.
     * <p/>
     * @param weightx ��� � ����������� x
     * @param weighty ��� � ����������� y
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * ������������� ������� ���������� ������������ ��� ������.
     * <p/>
     * @param distance ������� �� ���� ������������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * ������������� ������� ���������� ������������ ��� ������.
     * <p/>
     * @param top    ������ ������� ����� ���������� ������������
     * @param left   ������ ����� ����� ���������� ������������
     * @param bottom ������ ������ ����� ���������� ������������
     * @param right  ������ ������ ����� ���������� ������������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * ������������� ���������� ����������.
     * <p/>
     * @param ipadx ���������� ���������� � ����������� x
     * @param ipady ���������� ���������� � ����������� y
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }

}
