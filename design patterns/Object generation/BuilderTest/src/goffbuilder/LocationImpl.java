package goffbuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class LocationImpl implements Location
{
   public LocationImpl()
   {
   }

   public LocationImpl(String newLocation)
   {
      this.location = newLocation;
   }

   @Override
   public String getLocation()
   {
      return location;
   }

   @Override
   public void setLocation(String newLocation)
   {
      location = newLocation;
   }

   @Override
   public String toString()
   {
      return location;
   }

   private String location;
}
