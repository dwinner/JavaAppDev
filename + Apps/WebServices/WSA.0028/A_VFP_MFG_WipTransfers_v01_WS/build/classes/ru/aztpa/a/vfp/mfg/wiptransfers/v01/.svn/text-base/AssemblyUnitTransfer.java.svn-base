package ru.aztpa.a.vfp.mfg.wiptransfers.v01;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Единица перемещения одной ДСЕ
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0
 */
public class AssemblyUnitTransfer implements Comparable<AssemblyUnitTransfer>
{
   private final int year; // Год плана
   private final int month;   // Месяц плана
   private final String docNum;  // Номер сопроводительной накладной
   private final XMLGregorianCalendar docCal; // Дата документа (сопроводительной накладной)
   private final XMLGregorianCalendar lastUpdateCal;  // Дата последнего изменения сопроводительной накладной
   private final String dispather;  // Диспетчер
   private final int site; // Участок цеха отправителя ДСЕ
   private final String targetCode; // Код получателя ДСЕ (Склад или цех)
   private final String targetName; // Имя получателя ДСЕ (Склад или цех)
   private final String itemCode;   // Код ДСЕ
   private final String itemName;   // Имя ДСЕ
   private final double quantity;   // Перемещенное кол-во
   private final double itemCost;   // Условная стоимость
   private final String materialCode;  // Код матерала
   private final String materialName;  // Имя материала
   private final String materialSort;  // Марка материала
   private final String meltCode;   // Номер плавки
   private final String physicalProperties;  // Механические свойства
   private final String chemicalProperties;  // Химический состав
   private final int itemsPerBillet;   // Кол-во деталей в заготовке
   private final String outsourceItemCode;   // Код позиции - заготовки ОВК, из которой сделана деталь
   private final String outsourceItemName;   // Наименование позиции - заготовки ОВК, из которой сделана деталь
   private final double outsourceItemWeight; // Вес заготовки ОВК в кг
   private final String finishedGoodFigure;  // Конструкторское обозначение готового изделия, в состав которого входит ДСЕ
   private final String certificate;   // Паспорт или сертификат
   private final String itemSize;   // Размер ДСЕ
   private final String notes;   // Примечание

   /**
    * Закрытый конструктор единицы перемещения одной ДСЕ
    *
    * @param year Год плана
    * @param month Месяц плана
    * @param docNum Номер сопроводительной накладной
    * @param docCal Дата документа (сопроводительной накладной)
    * @param lastUpdateCal Дата последнего изменения сопроводительной накладной
    * @param dispather Диспетчер
    * @param site Участок цеха отправителя ДСЕ
    * @param targetCode Код получателя ДСЕ
    * @param targetName Имя получателя ДСЕ
    * @param itemCode Код ДСЕ
    * @param itemName Имя ДСЕ
    * @param quantity Перемещенное кол-во
    * @param itemCost Условная стоимость
    * @param materialCode Код матерала
    * @param materialName Имя материала
    * @param materialSort Марка материала
    * @param meltCode Номер плавки
    * @param physicalProperties Механические свойства
    * @param chemicalProperties Химический состав
    * @param itemsPerBillet Кол-во деталей в заготовке
    * @param outsourceItemCode Код позиции - заготовки ОВК, из которой сделана деталь
    * @param outsourceItemName Наименование позиции - заготовки ОВК, из которой сделана деталь
    * @param outsourceItemWeight Вес заготовки ОВК в кг
    * @param finishedGoodFigure Конструкторское обозначение готового изделия, в состав которого входит ДСЕ
    * @param certificate Паспорт или сертификат
    * @param itemSize Размер ДСЕ
    * @param notes Примечание
    */
   private AssemblyUnitTransfer(int year, int month, String docNum, XMLGregorianCalendar docCal,
                                XMLGregorianCalendar lastUpdateCal, String dispather, int site,
                                String targetCode, String targetName, String itemCode, String itemName,
                                double quantity, double itemCost, String materialCode, String materialName,
                                String materialSort, String meltCode, String physicalProperties,
                                String chemicalProperties, int itemsPerBillet, String outsourceItemCode,
                                String outsourceItemName, double outsourceItemWeight,
                                String finishedGoodFigure,
                                String certificate, String itemSize, String notes)
   {
      this.year = year;
      this.month = month;
      this.docNum = docNum;
      this.docCal = docCal;
      this.lastUpdateCal = lastUpdateCal;
      this.dispather = dispather;
      this.site = site;
      this.targetCode = targetCode;
      this.targetName = targetName;
      this.itemCode = itemCode;
      this.itemName = itemName;
      this.quantity = quantity;
      this.itemCost = itemCost;
      this.materialCode = materialCode;
      this.materialName = materialName;
      this.materialSort = materialSort;
      this.meltCode = meltCode;
      this.physicalProperties = physicalProperties;
      this.chemicalProperties = chemicalProperties;
      this.itemsPerBillet = itemsPerBillet;
      this.outsourceItemCode = outsourceItemCode;
      this.outsourceItemName = outsourceItemName;
      this.outsourceItemWeight = outsourceItemWeight;
      this.finishedGoodFigure = finishedGoodFigure;
      this.certificate = certificate;
      this.itemSize = itemSize;
      this.notes = notes;
   }

   /**
    * Год плана
    *
    * @return Год плана
    */
   public int getYear()
   {
      return year;
   }

   /**
    * Месяц плана
    *
    * @return Месяц плана
    */
   public int getMonth()
   {
      return month;
   }

   /**
    * Номер сопроводительной накладной
    *
    * @return Номер сопроводительной накладной
    */
   public String getDocNum()
   {
      return docNum;
   }

   /**
    * Дата документа (сопроводительной накладной)
    *
    * @return Дата документа (сопроводительной накладной)
    */
   public XMLGregorianCalendar getDocCal()
   {
      return docCal;
   }

   /**
    * Дата последнего изменения сопроводительной накладной
    *
    * @return Дата последнего изменения сопроводительной накладной
    */
   public XMLGregorianCalendar getLastUpdateCal()
   {
      return lastUpdateCal;
   }

   /**
    * Диспетчер
    *
    * @return Диспетчер
    */
   public String getDispather()
   {
      return dispather;
   }

   /**
    * Участок цеха отправителя ДСЕ
    *
    * @return Участок цеха отправителя ДСЕ
    */
   public int getSite()
   {
      return site;
   }

   /**
    * Код получателя ДСЕ
    *
    * @return Код получателя ДСЕ
    */
   public String getTargetCode()
   {
      return targetCode;
   }

   /**
    * Имя получателя ДСЕ
    *
    * @return Имя получателя ДСЕ
    */
   public String getTargetName()
   {
      return targetName;
   }

   /**
    * Код ДСЕ
    *
    * @return Код ДСЕ
    */
   public String getItemCode()
   {
      return itemCode;
   }

   /**
    * Имя ДСЕ
    *
    * @return Имя ДСЕ
    */
   public String getItemName()
   {
      return itemName;
   }

   /**
    * Перемещенное кол-во
    *
    * @return Перемещенное кол-во
    */
   public double getQuantity()
   {
      return quantity;
   }

   /**
    * Условная стоимость
    *
    * @return Условная стоимость
    */
   public double getItemCost()
   {
      return itemCost;
   }

   /**
    * Код матерала
    *
    * @return Код матерала
    */
   public String getMaterialCode()
   {
      return materialCode;
   }

   /**
    * Имя материала
    *
    * @return Имя материала
    */
   public String getMaterialName()
   {
      return materialName;
   }

   /**
    * Марка материала
    *
    * @return Марка материала
    */
   public String getMaterialSort()
   {
      return materialSort;
   }

   /**
    * Номер плавки
    *
    * @return Номер плавки
    */
   public String getMeltCode()
   {
      return meltCode;
   }

   /**
    * Механические свойства
    *
    * @return Механические свойства
    */
   public String getPhysicalProperties()
   {
      return physicalProperties;
   }

   /**
    * Химический состав
    *
    * @return Химический состав
    */
   public String getChemicalProperties()
   {
      return chemicalProperties;
   }

   /**
    * Кол-во деталей в заготовке
    *
    * @return Кол-во деталей в заготовке
    */
   public int getItemsPerBillet()
   {
      return itemsPerBillet;
   }

   /**
    * Код позиции - заготовки ОВК, из которой сделана деталь
    *
    * @return Код позиции - заготовки ОВК, из которой сделана деталь
    */
   public String getOutsourceItemCode()
   {
      return outsourceItemCode;
   }

   /**
    * Наименование позиции - заготовки ОВК, из которой сделана деталь
    *
    * @return Наименование позиции - заготовки ОВК, из которой сделана деталь
    */
   public String getOutsourceItemName()
   {
      return outsourceItemName;
   }

   /**
    * Вес заготовки ОВК в кг
    *
    * @return Вес заготовки ОВК в кг
    */
   public double getOutsourceItemWeight()
   {
      return outsourceItemWeight;
   }

   /**
    * Конструкторское обозначение готового изделия, в состав которого входит ДСЕ
    *
    * @return Конструкторское обозначение готового изделия, в состав которого входит ДСЕ
    */
   public String getFinishedGoodFigure()
   {
      return finishedGoodFigure;
   }

   /**
    * Паспорт или сертификат
    *
    * @return Паспорт или сертификат
    */
   public String getCertificate()
   {
      return certificate;
   }

   /**
    * Размер ДСЕ
    *
    * @return Размер ДСЕ
    */
   public String getItemSize()
   {
      return itemSize;
   }

   /**
    * Примечание
    *
    * @return Примечание
    */
   public String getNotes()
   {
      return notes;
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 97 * hash + this.year;
      hash = 97 * hash + this.month;
      hash = 97 * hash + (this.docNum != null ? this.docNum.hashCode() : 0);
      hash = 97 * hash + (this.targetCode != null ? this.targetCode.hashCode() : 0);
      hash = 97 * hash + (this.itemCode != null ? this.itemCode.hashCode() : 0);
      hash = 97 * hash + (this.materialCode != null ? this.materialCode.hashCode() : 0);
      hash = 97 * hash + (this.materialSort != null ? this.materialSort.hashCode() : 0);
      hash = 97 * hash + (this.meltCode != null ? this.meltCode.hashCode() : 0);
      hash = 97 * hash + (this.physicalProperties != null ? this.physicalProperties.hashCode() : 0);
      hash = 97 * hash + (this.chemicalProperties != null ? this.chemicalProperties.hashCode() : 0);
      hash = 97 * hash + this.itemsPerBillet;
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final AssemblyUnitTransfer other = (AssemblyUnitTransfer) obj;
      if (this.year != other.getYear())
      {
         return false;
      }
      if (this.month != other.getMonth())
      {
         return false;
      }
      if ((this.docNum == null) ? (other.docNum != null) : !this.docNum.equals(other.docNum))
      {
         return false;
      }
      if ((this.targetCode == null) ? (other.targetCode != null) : !this.targetCode.equals(other.targetCode))
      {
         return false;
      }
      if ((this.itemCode == null) ? (other.itemCode != null) : !this.itemCode.equals(other.itemCode))
      {
         return false;
      }
      if ((this.materialCode == null) ? (other.materialCode != null) : !this.materialCode.equals(
        other.materialCode))
      {
         return false;
      }
      if ((this.materialSort == null) ? (other.materialSort != null) : !this.materialSort.equals(
        other.materialSort))
      {
         return false;
      }
      if ((this.meltCode == null) ? (other.meltCode != null) : !this.meltCode.equals(other.meltCode))
      {
         return false;
      }
      if ((this.physicalProperties == null) ? (other.physicalProperties != null) : !this.physicalProperties.
        equals(other.physicalProperties))
      {
         return false;
      }
      if ((this.chemicalProperties == null) ? (other.chemicalProperties != null) : !this.chemicalProperties.
        equals(other.chemicalProperties))
      {
         return false;
      }
      if (this.itemsPerBillet != other.itemsPerBillet)
      {
         return false;
      }
      return true;
   }

   @Override
   public int compareTo(AssemblyUnitTransfer o)
   {
      return getLastUpdateCal().compare(o.getLastUpdateCal());
   }

   /**
    * Генератор объектов внешнего класса.
    */
   public static class Builder
   {
      private int year;
      private int month;
      private String docNum;
      private XMLGregorianCalendar docCal;
      private XMLGregorianCalendar lastUpdateCal;
      private String dispather;
      private int site;
      private String targetCode;
      private String targetName;
      private String itemCode;
      private String itemName;
      private double quantity;
      private double itemCost;
      private String materialCode = "";
      private String materialName;
      private String materialSort = "";
      private String meltCode = "";
      private String physicalProperties = "";
      private String chemicalProperties = "";
      private int itemsPerBillet;
      private String outsourceItemCode;
      private String outsourceItemName;
      private double outsourceItemWeight;
      private String finishedGoodFigure;
      private String certificate;
      private String itemSize;
      private String notes;

      public Builder()
      {
      }

      public Builder setYear(int year)
      {
         this.year = year;
         return this;
      }

      public Builder setMonth(int month)
      {
         this.month = month;
         return this;
      }

      public Builder setDocNum(String docNum)
      {
         this.docNum = docNum;
         return this;
      }

      public Builder setDocCal(XMLGregorianCalendar docCal)
      {
         this.docCal = docCal;
         return this;
      }

      public Builder setLastUpdateCal(XMLGregorianCalendar lastUpdateCal)
      {
         this.lastUpdateCal = lastUpdateCal;
         return this;
      }

      public Builder setDispather(String dispather)
      {
         this.dispather = dispather;
         return this;
      }

      public Builder setSite(int site)
      {
         this.site = site;
         return this;
      }

      public Builder setTargetCode(String targetCode)
      {
         this.targetCode = targetCode;
         return this;
      }

      public Builder setTargetName(String targetName)
      {
         this.targetName = targetName;
         return this;
      }

      public Builder setItemCode(String itemCode)
      {
         this.itemCode = itemCode;
         return this;
      }

      public Builder setItemName(String itemName)
      {
         this.itemName = itemName;
         return this;
      }

      public Builder setQuantity(double quantity)
      {
         this.quantity = quantity;
         return this;
      }

      public Builder setItemCost(double itemCost)
      {
         this.itemCost = itemCost;
         return this;
      }

      public Builder setMaterialCode(String materialCode)
      {
         this.materialCode = materialCode;
         return this;
      }

      public Builder setMaterialName(String materialName)
      {
         this.materialName = materialName;
         return this;
      }

      public Builder setMaterialSort(String materialSort)
      {
         this.materialSort = materialSort;
         return this;
      }

      public Builder setMeltCode(String meltCode)
      {
         this.meltCode = meltCode;
         return this;
      }

      public Builder setPhysicalProperties(String physicalProperties)
      {
         this.physicalProperties = physicalProperties;
         return this;
      }

      public Builder setChemicalProperties(String chemicalProperties)
      {
         this.chemicalProperties = chemicalProperties;
         return this;
      }

      public Builder setItemsPerBillet(int itemsPerBillet)
      {
         this.itemsPerBillet = itemsPerBillet;
         return this;
      }

      public Builder setOutsourceItemCode(String outsourceItemCode)
      {
         this.outsourceItemCode = outsourceItemCode;
         return this;
      }

      public Builder setOutsourceItemName(String outsourceItemName)
      {
         this.outsourceItemName = outsourceItemName;
         return this;
      }

      public Builder setOutsourceItemWeight(double outsourceItemWeight)
      {
         this.outsourceItemWeight = outsourceItemWeight;
         return this;
      }

      public Builder setFinishedGoodFigure(String finishedGoodFigure)
      {
         this.finishedGoodFigure = finishedGoodFigure;
         return this;
      }

      public Builder setCertificate(String certificate)
      {
         this.certificate = certificate;
         return this;
      }

      public Builder setItemSize(String itemSize)
      {
         this.itemSize = itemSize;
         return this;
      }

      public Builder setNotes(String notes)
      {
         this.notes = notes;
         return this;
      }

      public AssemblyUnitTransfer createAssemblyUnitTransfer()
      {
         return new AssemblyUnitTransfer(year, month, docNum, docCal, lastUpdateCal, dispather, site,
                                         targetCode,
                                         targetName, itemCode, itemName, quantity, itemCost, materialCode,
                                         materialName, materialSort, meltCode, physicalProperties,
                                         chemicalProperties, itemsPerBillet, outsourceItemCode,
                                         outsourceItemName,
                                         outsourceItemWeight, finishedGoodFigure, certificate, itemSize, notes);
      }
   }

}
