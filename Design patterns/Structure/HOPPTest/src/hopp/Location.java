package hopp;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 24.08.12
 * Time: 7:56
 * To change this template use File | Settings | File Templates.
 */
public interface Location extends Serializable
{
   String getLocation();
   void setLocation(String newLocation);
}
