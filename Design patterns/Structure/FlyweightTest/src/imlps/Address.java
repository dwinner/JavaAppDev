package imlps;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public interface Address extends Serializable
{
   String getType();
   String getDescription();
   String getStreet();
   String getCity();
   String getState();
   String getZipCode();

   void setType(String newType);
   void setDescription(String newDescription);
   void setStreet(String newStreet);
   void setCity(String newCity);
   void setState(String newState);
   void setZipCode(String newZip);

   String EOL_STRING = System.getProperty("line.separator");
   String SPACE = " ";
   String COMMA = ",";
}
