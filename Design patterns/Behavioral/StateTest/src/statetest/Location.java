package statetest;

import java.io.Serializable;

public interface Location extends Serializable
{
   String getLocation();

   void setLocation(String newLocation);
}
