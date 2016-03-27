package command;

import java.io.Serializable;

/**
 *
 * @author oracle_pr1
 */
public interface Location extends Serializable
{
   String getLocation();

   void setLocation(String newLocation);
}
