package ru.aztpa.a.ak.eng.bom.v01;

/**
 * Entity-класс о составе одного изделия
 * @version 1.0.0 12.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class ProductRecord
{
    public final static int FAIL_LINE_NUM = 99; // Нераспознанный номер строки спецификации.
    public final static String FAIL_COMP_NAME = ""; // Нераспознанное имя компонента

    // ----------------------- Имена справочников для поиска -----------------------------------------------------------
    public final static String ASSEMBLY_UNITS = "sp_org";   // Сборочные единицы
    public final static String DETAILS = ASSEMBLY_UNITS;    // Детали
    public final static String STANDARD_PRODUCTS = "sp_st"; // Стандартные изделия
    public final static String OTHER_PRODUCTS = "sp_pr";    // Прочие изделия
    public final static String MATERIALS = "sp_mat";        // Материалы
    public final static String DUMMY = "dummy";             // Заглушка

    // ----------------------- Поля ------------------------------------------------------------------------------------
    private String assemblyFigure;  // Обозначение изделия.
    private int sectionName;    // Раздел спецификации.
    private int lineNum;    // Номер строки спецификации.
    private String compFigure;  // Обозначение компонента.
    private double quantity;    // Количество компонентов.

    /**
     * Конструктор записи.
     * @param assemblyFigure Обозначение изделия.
     * @param sectionName    Раздел спецификации.
     * @param lineNum        Номер строки спецификации.
     * @param compFigure     Обозначение компонента.
     * @param quantity       Количество компонентов.
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
     * Получение обозначения изделия.
     * @return Обозначение изделия.
     */
    public String getAssemblyFigure() { return assemblyFigure; }

    /**
     * Установка обозначения изделия.
     * @param assemblyFigure Обозначение изделия.
     */
    public void setAssemblyFigure(String assemblyFigure) { this.assemblyFigure = assemblyFigure; }

    /**
     * Получение обозначения компонента.
     * @return Обозначение компонента.
     */
    public String getCompFigure() { return compFigure; }

    /**
     * Установка обозначения компонента.
     * @param compFigure Обозначение компонента.
     */
    public void setCompFigure(String compFigure) { this.compFigure = compFigure; }

    /**
     * Получение номера строки спецификации.
     * @return Номер строки спецификации.
     */
    public int getLineNum() { return lineNum; }

    /**
     * Установка номера строки спецификации.
     * @param lineNum Номер строки спецификации.
     */
    public void setLineNum(int lineNum) { this.lineNum = lineNum; }

    /**
     * Получение количества компонентов.
     * @return Количество компонентов.
     */
    public double getQuantity() { return quantity; }

    /**
     * Установка количества компонентов.
     * @param quantity Количество компонентов.
     */
    public void setQuantity(double quantity) { this.quantity = quantity; }

    /**
     * Получение раздела спецификации.
     * @return Раздел спецификации.
     */
    public int getSectionName() { return sectionName; }

    /**
     * Установка раздела спецификации.
     * @param sectionName Раздел спецификации.
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
     * Получение имени раздела спецификации по его номеру.
     * @param sectionOrder Раздел спецификации.
     * @return Описательное имя раздела спецификации.
     */
    public static String obtainSectionName(int sectionOrder)
    {
        switch (sectionOrder)
        {
            case 3:     return "Сборочные единицы";
            case 4:     return "Детали";
            case 5:     return "Стандартные изделия";
            case 6:     return "Прочие изделия";
            case 7:     return "Материалы";
            default:    return "Другой раздел спецификации";
        }
    }

    /**
     * Получение наиболее подходящего справочника для поиска по разделу спецификации
     * @param sectionOrder Раздел спецификации.
     * @return Имя наиболее вероятного справочника-кандидата.
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
