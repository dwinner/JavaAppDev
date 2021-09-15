package ru.aztpa.a.oaa.mfg.plan.v01.process;

/**
 * Utility-����� ��� ��������� ����� ����� ��������� ����
 * @version 1.0.0 04.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class ProcessFields
{
    private ProcessFields() { assert false; }

    /**
     * ��������� ���� ����������� ���� ��� �������� ���� � ��������� ����� �����.
     * @param field     ���� ��� ���������
     * @param trimLimit ���������� ����� ���� � ����
     * @return ������, ������� ��� �������� ����
     */
    public static String processStringField(String field, int trimLimit)
    {
        if (field == null)
            return "NULL";
        String lField = field.trim();
        if (lField.length() > trimLimit)
            lField = lField.substring(0, trimLimit);
        return "'" + lField + "'";
    }

    /**
     * ��������� ���� ����������� ���� ��� �������� ���� ��� ��������� ����� �����.
     * @param field ���� ��� ���������
     * @return ������, ������� ��� �������� ����
     */
    public static String processStringField(String field)
    {
        if (field == null)
            return "NULL";
        return "'" + field + "'";
    }

    /**
     * ��������� ������ � �������������� � ����� ��������
     * @param field ���� ��� ���������
     * @return ����� ���� int, ������� ��� �������� ����
     */
    public static int processStringToInteger(String field)
    {
        if (field == null)
        {
            NullPointerException npEx = new NullPointerException("This case is wrong");
            throw new RuntimeException(npEx);
        }
        if (0 != (field = field.trim()).length())
        {
            int intValue;
            try
            {
                intValue = Integer.parseInt(field);
            }
            catch (NumberFormatException nfEx)
            {
                throw new RuntimeException(nfEx);
            }
            return intValue;
        }
        throw new RuntimeException("Value to parse must be not empty");
    }
}
