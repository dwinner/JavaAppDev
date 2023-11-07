package hopp;

/**
* Created with IntelliJ IDEA.
* User: oracle_pr1
* Date: 24.08.12
* Time: 7:58
* To change this template use File | Settings | File Templates.
*/
public class LocationImpl implements Location
{
   public LocationImpl() { }

   public LocationImpl(String newLocation)
   {
      location = newLocation;
   }

   @Override
   public String getLocation()
   {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
   }

   @Override
   public void setLocation(String newLocation)
   {
      //To change body of implemented methods use File | Settings | File Templates.
   }

   @Override
   public String toString()
   {
      return "hopp.LocationImpl{" +
        "location='" + location + '\'' +
        '}';
   }

   private String location;
}
