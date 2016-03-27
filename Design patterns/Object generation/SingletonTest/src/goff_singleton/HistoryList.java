package goff_singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.08.12
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 */
public class HistoryList
{
   public static HistoryList getInstance()
   {
      return instance;
   }

   public void addCommand(String command)
   {
      history.add(command);
   }

   public Object undoCommand()
   {
      return history.remove(history.size() - 1);
   }

   @Override public String toString()
   {
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < history.size(); i++)
      {
         result.append("  ");
         result.append(history.get(i));
         result.append("\n");
      }
      return result.toString();
   }

   private HistoryList() { }

   private List<String> history = Collections.synchronizedList(new ArrayList<String>());
   private static HistoryList instance = new HistoryList();
}
