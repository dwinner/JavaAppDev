package command;

/**
 *
 * @author oracle_pr1
 */
public interface UndoableCommand extends Command
{
   void undo();

   void redo();
}
