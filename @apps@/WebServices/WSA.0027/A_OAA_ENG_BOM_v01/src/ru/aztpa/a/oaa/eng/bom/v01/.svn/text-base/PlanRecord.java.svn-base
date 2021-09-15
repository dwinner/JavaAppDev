package ru.aztpa.a.oaa.eng.bom.v01;

import java.sql.Date;

/**
 * Entity-класс дл€ инкапсул€ции одной строки плана.
 * @author jdeveloper@aztpa.ru
 */
public class PlanRecord
{
    private String itemFigure;
    private String itemCode;
    private String itemDesc;
    private Date planMonth;
    private String planName;
    private String departmentCode;
    private int odpFlag;
    private int spkFlag;

    public PlanRecord(String itemFigure,
       String itemCode,
       String itemDesc,
       Date planMonth,
       String planName,
       String departmentCode,
       int odpFlag,
       int spkFlag)
    {
        this.itemFigure = itemFigure;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.planMonth = planMonth;
        this.planName = planName;
        this.departmentCode = departmentCode;
        this.odpFlag = odpFlag;
        this.spkFlag = spkFlag;
    }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public String getItemDesc() { return itemDesc; }
    public void setItemDesc(String itemDesc) { this.itemDesc = itemDesc; }

    public String getItemFigure() { return itemFigure; }
    public void setItemFigure(String itemFigure) { this.itemFigure = itemFigure; }

    public int getOdpFlag() { return odpFlag; }
    public void setOdpFlag(int odpFlag) { this.odpFlag = odpFlag; }

    public Date getPlanMonth() { return planMonth; }
    public void setPlanMonth(Date planMonth) { this.planMonth = planMonth; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public int getSpkFlag() { return spkFlag; }
    public void setSpkFlag(int spkFlag) { this.spkFlag = spkFlag; }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final PlanRecord other = (PlanRecord) obj;
        if ((this.itemFigure == null)
           ? (other.itemFigure != null)
           : !this.itemFigure.equals(other.itemFigure)
           ) return false;
        if ((this.itemCode == null)
           ? (other.itemCode != null)
           : !this.itemCode.equals(other.itemCode)
           ) return false;
        if ((this.itemDesc == null)
           ? (other.itemDesc != null)
           : !this.itemDesc.equals(other.itemDesc)
           ) return false;
        if (this.planMonth != other.planMonth
           && (this.planMonth == null
           || !this.planMonth.equals(other.planMonth))
           ) return false;
        if ((this.planName == null)
           ? (other.planName != null)
           : !this.planName.equals(other.planName)
           ) return false;
        if ((this.departmentCode == null)
           ? (other.departmentCode != null)
           : !this.departmentCode.equals(other.departmentCode)
           ) return false;
        if (this.odpFlag != other.odpFlag) return false;
        if (this.spkFlag != other.spkFlag) return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + (this.itemFigure != null ? this.itemFigure.hashCode() : 0);
        hash = 67 * hash + (this.itemCode != null ? this.itemCode.hashCode() : 0);
        hash = 67 * hash + (this.itemDesc != null ? this.itemDesc.hashCode() : 0);
        hash = 67 * hash + (this.planMonth != null ? this.planMonth.hashCode() : 0);
        hash = 67 * hash + (this.planName != null ? this.planName.hashCode() : 0);
        hash = 67 * hash + (this.departmentCode != null ? this.departmentCode.hashCode() : 0);
        hash = 67 * hash + this.odpFlag;
        hash = 67 * hash + this.spkFlag;
        return hash;
    }

    @Override
    public String toString()
    {
        return "PlanRecord{" + "itemFigure=" + itemFigure + ", "
           + "itemCode=" + itemCode + ", "
           + "itemDesc=" + itemDesc + ", "
           + "planMonth=" + planMonth + ", "
           + "planName=" + planName + ", "
           + "departmentCode=" + departmentCode + ", "
           + "odpFlag=" + odpFlag + ", "
           + "spkFlag=" + spkFlag + '}';
    }
}
