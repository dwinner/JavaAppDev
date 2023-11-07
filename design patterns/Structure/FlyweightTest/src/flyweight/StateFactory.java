package flyweight;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class StateFactory
{
   public static State getCurrentState()
   {
      return currentState;
   }

   public static void setCurrentState(State state)
   {
      currentState = state;
   }

   public static final State CLEAN = new CleanState();
   public static final State DIRTY = new DirtyState();

   private StateFactory() { }
   private static State currentState = CLEAN;
}
