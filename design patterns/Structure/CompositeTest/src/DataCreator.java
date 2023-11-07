import composite.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 9:48
 * To change this template use File | Settings | File Templates.
 */
public class DataCreator
{
   public static void main(String[] args)
   {
      String fileName = args.length == 1 ? args[0] : DEFAULT_FILE;
      serialize(fileName);
   }

   public static void serialize(String fileName)
   {
      try
      {
         serializeToFile(createData(), fileName);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private static void serializeToFile(Serializable data, String fileName)
     throws IOException
   {
      try (ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         serOut.writeObject(data);
      }
   }

   private static Serializable createData()
   {
      Contact contact1 = new ContactImpl("Dennis", "Moore", "Managing Director", "Highway Man, LTD");
      Contact contact2 = new ContactImpl("Joseph", "Mongolfier", "High Flyer", "Ligher than Air Productions");
      Contact contact3 = new ContactImpl("Erik", "Njoll", "Nomad without Portfolio", "Nordik Trek Inc.");
      Contact contact4 = new ContactImpl("Lemming", "", "Principal Investigator", "BDA");

      Project<ProjectItem> project = new Project<>("IslandParadise", "Acquire a personal island paradise");
      Deliverable deliverable1 = new Deliverable("Island Paradise", "", contact1);
      Task<ProjectItem> task1 = new Task<>("Fortune", "Acquire a small fortune", contact4, 11.0);
      Task<ProjectItem> task2 = new Task<>("Isle", "Locate an island for sale", contact2, 7.5);
      Task<ProjectItem> task3 = new Task<>("Name", "Decide on a name for the island", contact3, 3.2);

      project.addProjectItem(deliverable1);
      project.addProjectItem(task1);
      project.addProjectItem(task2);
      project.addProjectItem(task3);

      Deliverable deliverable11 = new Deliverable("$1,000,000", "(total net worth after taxes)", contact1);
      Task<ProjectItem> task11 =
        new Task<>("Fortune1", "Use psychic hotline to predict winning lottery numbers", contact4, 2.5);
      Task<ProjectItem> task12 =
         new Task<>("Fortune2", "Invest winnings to ensure 50% annual interest", contact1, 14.0);
      task1.addProjectItem(task11);
      task1.addProjectItem(task12);
      task1.addProjectItem(deliverable11);

      Task<ProjectItem> task21 =
        new Task<>("Isle1", "Research whether climate is better in the Altantic or Pacific", contact1, 1.8);
      Task<ProjectItem> task22 =
        new Task<>("Isle2", "Locate an island for auction on EBay", contact4, 5.0);
      Task<ProjectItem> task23 =
        new Task<>("Isle2a", "Negotiate for sale of the island", contact3, 17.5);
      task2.addProjectItem(task21);
      task2.addProjectItem(task22);
      task2.addProjectItem(task23);

      Deliverable deliverable31 = new Deliverable("Island Name", "", contact1);
      task3.addProjectItem(deliverable31);

      return project;
   }

   private DataCreator() { }

   private static final String DEFAULT_FILE = "data.ser";
}
