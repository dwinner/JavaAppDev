package goffbuilder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public interface Location extends Serializable
{
   String getLocation();
   void setLocation(String newLocation);
}
