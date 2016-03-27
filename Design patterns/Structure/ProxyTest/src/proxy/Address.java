package proxy;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public interface Address extends Serializable
{
   String getAddress();
   String getType();
   String getDescription();
   String getStreet();
   String getCity();
   String getState();
   String getZipCode();

   void setType(String newType);
   void setDescription(String newDecrioption);
   void setStreet(String newStreet);
   void setCity(String newCity);
   void setState(String newState);
   void setZipCode(String newZip);

   String EOL_STRING = System.getProperty("line.separator");
   String SPACE = " ";
   String COMMA = ",";
}
