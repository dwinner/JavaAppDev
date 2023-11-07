import goff_factory.Contact;
import goff_factory.EditorGui;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.07.12
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] args)
   {
      System.out.println("Example for the FactoryMethod pattern");
      System.out.println();

      System.out.println("Creating a goff_factory.Contact object");
      System.out.println();
      Contact someone = new Contact();

      System.out.println("Creating a GUI editor for the goff_factory.Contact");
      System.out.println();
      System.out.println("The GUI defined in the goff_factory.EditorGui class is a truly generic editor.");
      System.out.println("It accepts an argument of type goff_factory.ItemEditor, and delegates");
      System.out.println("  all editing tasks to its goff_factory.ItemEditor and the associated GUI.");
      System.out.println(" The getEditor() Factory Method is used to obtain the goff_factory.ItemEditor");
      System.out.println(" for the example.");
      System.out.println();
      System.out.println("Notice that the editor in the top portion of the GUI is,");
      System.out.println(" in fact, returned by the goff_factory.ItemEditor belonging to the");
      System.out.println(" goff_factory.Contact class, and has appropriate fields for that class.");

      EditorGui runner = new EditorGui(someone.getEditor());
      runner.createGui();
   }
}
