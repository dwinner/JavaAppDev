package flyweight;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class DirtyState implements State
{
   @Override
   public void save(File file, Serializable serializable, int type)
     throws IOException
   {
      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
      {
         out.writeObject(serializable);
         decrementStateValue(type);
         if (type == 0)
            StateFactory.setCurrentState(StateFactory.CLEAN);
      }
   }

   @Override
   public void edit(int type)
   {
      incrementStateValue(type);
   }

   public void incrementStateValue(int stateType)
   {
      if (stateType > 0 && stateType <= MAXIMUM_STATE_VALUE)
         stateFlags |= stateType;
   }

   public void decrementStateValue(int stateType)
   {
      if (stateType > 0 && stateType <= MAXIMUM_STATE_VALUE)
         stateFlags ^= stateType;
   }

   private int stateFlags;
}
