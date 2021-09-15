package ru.aztpa.a.ak.eng.bom.v01;

/**
 * Entity-����� � ������� ������ �������
 * @version 1.0.0 12.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class ProductRecord
{
    public final static int FAIL_LINE_NUM = 99; // �������������� ����� ������ ������������.
    public final static String FAIL_COMP_NAME = ""; // �������������� ��� ����������

    // ----------------------- ����� ������������ ��� ������ -----------------------------------------------------------
    public final static String ASSEMBLY_UNITS = "sp_org";   // ��������� �������
    public final static String DETAILS = ASSEMBLY_UNITS;    // ������
    public final static String STANDARD_PRODUCTS = "sp_st"; // ����������� �������
    public final static String OTHER_PRODUCTS = "sp_pr";    // ������ �������
    public final static String MATERIALS = "sp_mat";        // ���������
    public final static String DUMMY = "dummy";             // ��������

    // ----------------------- ���� ------------------------------------------------------------------------------------
    private String assemblyFigure;  // ����������� �������.
    private int sectionName;    // ������ ������������.
    private int lineNum;    // ����� ������ ������������.
    private String compFigure;  // ����������� ����������.
    private double quantity;    // ���������� �����������.

    /**
     * ����������� ������.
     * @param assemblyFigure ����������� �������.
     * @param sectionName    ������ ������������.
     * @param lineNum        ����� ������ ������������.
     * @param compFigure     ����������� ����������.
     * @param quantity       ���������� �����������.
     */
    public ProductRecord(String assemblyFigure, int sectionName, int lineNum, String compFigure, double quantity)
    {
        this.assemblyFigure = assemblyFigure;
        this.sectionName = sectionName;
        this.lineNum = lineNum;
        this.compFigure = compFigure;
        this.quantity = quantity;
    }

    /**
     * ��������� ����������� �������.
     * @return ����������� �������.
     */
    public String getAssemblyFigure() { return assemblyFigure; }

    /**
     * ��������� ����������� �������.
     * @param assemblyFigure ����������� �������.
     */
    public void setAssemblyFigure(String assemblyFigure) { this.assemblyFigure = assemblyFigure; }

    /**
     * ��������� ����������� ����������.
     * @return ����������� ����������.
     */
    public String getCompFigure() { return compFigure; }

    /**
     * ��������� ����������� ����������.
     * @param compFigure ����������� ����������.
     */
    public void setCompFigure(String compFigure) { this.compFigure = compFigure; }

    /**
     * ��������� ������ ������ ������������.
     * @return ����� ������ ������������.
     */
    public int getLineNum() { return lineNum; }

    /**
     * ��������� ������ ������ ������������.
     * @param lineNum ����� ������ ������������.
     */
    public void setLineNum(int lineNum) { this.lineNum = lineNum; }

    /**
     * ��������� ���������� �����������.
     * @return ���������� �����������.
     */
    public double getQuantity() { return quantity; }

    /**
     * ��������� ���������� �����������.
     * @param quantity ���������� �����������.
     */
    public void setQuantity(double quantity) { this.quantity = quantity; }

    /**
     * ��������� ������� ������������.
     * @return ������ ������������.
     */
    public int getSectionName() { return sectionName; }

    /**
     * ��������� ������� ������������.
     * @param sectionName ������ ������������.
     */
    public void setSectionName(int sectionName) { this.sectionName = sectionName; }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProductRecord other = (ProductRecord) obj;
        if ((this.assemblyFigure == null)
            ? (other.getAssemblyFigure() != null)
            : !this.assemblyFigure.equals(other.getAssemblyFigure())
           )
            return false;
        if (this.sectionName != other.getSectionName())
            return false;
        if (this.lineNum != other.getLineNum())
            return false;
        if ((this.compFigure == null)
            ? (other.getCompFigure() != null)
            : !this.compFigure.equals(other.getCompFigure())
           )
            return false;
        if (Double.doubleToLongBits(this.quantity) != Double.doubleToLongBits(other.getQuantity()))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + (this.assemblyFigure != null ? this.assemblyFigure.hashCode() : 0);
        hash = 67 * hash + this.sectionName;
        hash = 67 * hash + this.lineNum;
        hash = 67 * hash + (this.compFigure != null ? this.compFigure.hashCode() : 0);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.quantity)
           ^ (Double.doubleToLongBits(this.quantity) >>> 32));
        return hash;
    }

    @Override
    public String toString()
    {
        return "ProductRecord{" + "assemblyFigure=" + assemblyFigure
           + ", sectionName=" + sectionName
           + ", lineNum=" + lineNum
           + ", compFigure=" + compFigure
           + ", quantity=" + quantity + '}';
    }

    /**
     * ��������� ����� ������� ������������ �� ��� ������.
     * @param sectionOrder ������ ������������.
     * @return ������������ ��� ������� ������������.
     */
    public static String obtainSectionName(int sectionOrder)
    {
        switch (sectionOrder)
        {
            case 3:     return "��������� �������";
            case 4:     return "������";
            case 5:     return "����������� �������";
            case 6:     return "������ �������";
            case 7:     return "���������";
            default:    return "������ ������ ������������";
        }
    }

    /**
     * ��������� �������� ����������� ����������� ��� ������ �� ������� ������������
     * @param sectionOrder ������ ������������.
     * @return ��� �������� ���������� �����������-���������.
     */
    public static String obtainTableName(int sectionOrder)
    {
        switch (sectionOrder)
        {
            case 3: case 4: return ASSEMBLY_UNITS;
            case 5: return STANDARD_PRODUCTS;
            case 6: return OTHER_PRODUCTS;
            case 7: return MATERIALS;
            default: return DUMMY;
        }
    }
}
