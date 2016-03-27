/**
 * Элемент с описанием и номером части.
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
public class Item
{
   /**
    * Создает элемент.
    * @param aDescription Описание элемента
    * @param aPartNumber Номер части элемента
    */
   public Item(String aDescription, int aPartNumber)
   {
      description = aDescription;
      partNumber = aPartNumber;
   }

   /**
    * Получает описание данного элемента.
    * @return Описание
    */
   public String getDescription()
   {
      return description;
   }

   public String toString()
   {
      return "[descripion=" + description + ", partNumber=" + partNumber + "]";
   }

   @LogEntry(logger = "global")
   public boolean equals(Object otherObject)
   {
      if (this == otherObject) return true;
      if (otherObject == null) return false;
      if (getClass() != otherObject.getClass()) return false;
      Item other = (Item) otherObject;
      return description.equals(other.description) && partNumber == other.partNumber;
   }

   @LogEntry(logger = "global")
   public int hashCode()
   {
      return 13 * description.hashCode() + 17 * partNumber;
   }

   private String description;
   private int partNumber;
}
