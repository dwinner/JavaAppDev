package statetest;

public class LocationImpl implements Location
{
   public LocationImpl()
   {
   }

   public LocationImpl(String newLocation)
   {
      location = newLocation;
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
   
   private String location;
}
