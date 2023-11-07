import facade.FacadeGui;
import facade.InternationalizationWizard;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Facade pattern");
      System.out.println();
      System.out.println("This code sample uses an InternationalizatgionWizard (a Facade)");
      System.out.println(" to manage communication between the rest of the application and");
      System.out.println(" a series of other classes.");
      System.out.println();
      System.out.println("The InternationalizatgionWizard maintains a colleciton of facade.Nation");
      System.out.println(" objects. When the setNation method is called, the wizard sets the");
      System.out.println(" default nation, updating the facade.Currency, facade.PhoneNumber and localized");
      System.out.println(" String resources (facade.InternationalizedText) available.");
      System.out.println();
      System.out.println("Calls to get Strings for the GUI, the currency symbol or the dialing");
      System.out.println(" prefix are routed through the Facade, the facade.InternationalizationWizard.");
      System.out.println();

      if (!(new File("data.ser").exists()))
      {
         DataCreator.serialize("data.ser");
      }

      System.out.println("Creating the facade.InternationalizationWizard and setting the nation to US.");
      System.out.println();
      InternationalizationWizard wizard = new InternationalizationWizard();
      wizard.setNation("US");

      System.out.println("Creating the facade.FacadeGui.");
      System.out.println();
      FacadeGui application = new FacadeGui(wizard);
      application.createGui();
      application.setNation(wizard.getNation("US"));
   }
}
