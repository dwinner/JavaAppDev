package facade;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class PhoneNumber
{
   public PhoneNumber(String internationalPrefix, String areaNumber, String netNumber)
   {
      this.internationalPrefix = internationalPrefix;
      this.areaNumber = areaNumber;
      this.netNumber = netNumber;
   }

   public static String getSelectedInterPrefix()
   {
      return selectedInterPrefix;
   }

   public static void setSelectedInterPrefix(String selectedInterPrefix)
   {
      PhoneNumber.selectedInterPrefix = selectedInterPrefix;
   }

   public String getInternationalPrefix()
   {
      return internationalPrefix;
   }

   public void setInternationalPrefix(String internationalPrefix)
   {
      this.internationalPrefix = internationalPrefix;
   }

   public String getAreaNumber()
   {
      return areaNumber;
   }

   public void setAreaNumber(String areaNumber)
   {
      this.areaNumber = areaNumber;
   }

   public String getNetNumber()
   {
      return netNumber;
   }

   public void setNetNumber(String netNumber)
   {
      this.netNumber = netNumber;
   }

   @Override
   public String toString()
   {
      return "facade.PhoneNumber{" +
        "internationalPrefix='" + internationalPrefix + '\'' +
        ", areaNumber='" + areaNumber + '\'' +
        ", netNumber='" + netNumber + '\'' +
        '}';
   }

   private static String selectedInterPrefix;
   private String internationalPrefix;
   private String areaNumber;
   private String netNumber;
}
