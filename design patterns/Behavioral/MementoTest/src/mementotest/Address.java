package mementotest;

import java.io.Serializable;

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
