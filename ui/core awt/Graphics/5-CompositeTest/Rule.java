import java.awt.AlphaComposite;

/**
 * �����, ����������� ������� �������-�����.
 */
public class Rule
{
    private String name;
    private String porterDuff1;
    private String porterDuff2;

    /**
     * �������� ������� �������-�����.
     * <p/>
     * @param name        ��� �������
     * @param porterDuff1 ������ ������ �������� �������-�����
     * @param porterDuff2 ������ ������ �������� �������-�����
     */
    public Rule(String name, String porterDuff1, String porterDuff2)
    {
        this.name = name;
        this.porterDuff1 = porterDuff1;
        this.porterDuff2 = porterDuff2;
    }

    /**
     * �����, ���������� ���������� ��������� ������� �������.
     * <p/>
     * @return ����������
     */
    public String getExplanation()
    {
        StringBuilder r = new StringBuilder("Source ");
        switch (porterDuff2)
        {
            case "  ":
                r.append("clears");
                break;
            case " S":
                r.append("overwrites");
                break;
            case "DS":
                r.append("blends with");
                break;
            case " D":
                r.append("alpha modifies");
                break;
            case "D ":
                r.append("alpha complement modifies");
                break;
            case "DD":
                r.append("does not affect");
                break;
        }

        r.append(" destination");
        if (porterDuff1.equals(" S"))
        {
            r.append(" and overwrites empty pixels");
        }
        r.append(".");
        return r.toString();
    }

    @Override
    public String toString()
    {
        return name;
    }

    /**
     * �����, ���������� �������� ������� ������� � ������ AlphaComposite.
     * <p/>
     * @return ��������� ������ AlphaComposite ��� -1, ���� ���������� ��������� ���.
     */
    public int getValue()
    {
        try
        {
            try
            {
                return (int) AlphaComposite.class.getField(name).get(null);
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                return -1;
            }
        }
        catch (NoSuchFieldException | SecurityException ex)
        {
            return -1;
        }
    }
}
