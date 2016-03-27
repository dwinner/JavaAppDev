package userbeans;

/**
 * Enum for index page navigation.
 *
 * @author Denis
 * @version demo 21-12-2012
 */
public enum IndexNavigationRules
{
   PREVIEW("preview"),
   ERRORDATE("errordate");

   public String getOutcome()
   {
      return outcome;
   }

   @Override
   public String toString()
   {
      return getOutcome();
   }

   private IndexNavigationRules(String anOutcome)
   {
      outcome = anOutcome;
   }

   private String outcome;
}
