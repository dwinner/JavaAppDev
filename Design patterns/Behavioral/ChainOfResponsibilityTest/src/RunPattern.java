import chain.Project;
import chain.ProjectItem;

import java.io.File;
import java.util.Iterator;

public class RunPattern
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Chain of Responsibility pattern");
      System.out.println();
      System.out.println("This code uses chain of responsibility to obtain");
      System.out.println(" the owner for a particular chain.ProjectItem, and to");
      System.out.println(" build up a list of project details. In each case,");
      System.out.println(" a call to the appropriate getter method, getOwner");
      System.out.println(" or getDetails, will pass the method call up the");
      System.out.println(" project tree.");
      System.out.println("For getOwner, the call will return the first non-null");
      System.out.println(" owner field encountered. For getDetails, the method");
      System.out.println(" will build a series of details, stopping when it");
      System.out.println(" reaches a chain.ProjectItem that is designated as a");
      System.out.println(" primary task.");
      System.out.println();

      System.out.println("Deserializing a test chain.Project for Visitor pattern");
      System.out.println();
      if (!(new File("data.ser").exists()))
      {
         DataCreator.serialize("data.ser");
      }
      Project project = (Project) DataRetriever.deserializeData("data.ser");

      System.out.println("Retrieving Owner and details for each item in the chain.Project");
      System.out.println();
      getItemInfo(project);
   }

   private static void getItemInfo(ProjectItem item)
   {
      System.out.println("chain.ProjectItem: " + item);
      System.out.println("  Owner: " + item.getOwner());
      System.out.println("  Details: " + item.getDetails());
      System.out.println();
      if (item.getProjectItems() != null)
      {
         Iterator subElements = item.getProjectItems().iterator();
         while (subElements.hasNext())
         {
            getItemInfo((ProjectItem) subElements.next());
         }
      }
   }
}