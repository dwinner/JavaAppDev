package flyweight;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class CleanState implements State
{
   @Override
   public void save(File file, Serializable serializable, int type)
     throws IOException
   {

   }

   @Override
   public void edit(int type)
   {
      StateFactory.setCurrentState(StateFactory.DIRTY);
      ((DirtyState) StateFactory.DIRTY).incrementStateValue(type);
   }
}
