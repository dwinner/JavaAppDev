package ru.aztpa.a.tp.mfg.process.v01;

/**
 * ������������ ���� ��� ������������������ ����������
 * <p/>
 * @author jdeveloper@aztpa.ru 15.03.2012
 * @version 1.0
 */
public enum JNDIContextEnum
{
    ZAGTP_BAS("ZAGTP_BAS"), // ������ ��������������� ���������
    ZAGTP_OPE("ZAGTP_OPE"), // ������ �������� ��� ������� �������� (������� <=> dbf-����)
    ZAGTP_PER("ZAGTP_PER"); // ������ ��������� ��� ������� �������� (������� <=> dbf-����)

    private JNDIContextEnum(String contextName)
    {
        this.contextName = contextName;
    }
    private String contextName;
}
