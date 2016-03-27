import composite.Project;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Composite pattern");
      System.out.println();
      System.out.println("This code sample will propagate a method call throughout");
      System.out.println(" a tree structure. The tree represents a project, and is");
      System.out.println(" composed of three kinds of ProjectItems - composite.Project, composite.Task,");
      System.out.println(" and composite.Deliverable. Of these three classes, composite.Project and composite.Task");
      System.out.println(" can store an Linked list of ProjectItems. This means that");
      System.out.println(" they can act as branch nodes for our tree. The composite.Deliverable");
      System.out.println(" is a terminal node, since it cannot hold any ProjectItems.");
      System.out.println();
      System.out.println("In this example, the method defined by composite.ProjectItem,");
      System.out.println(" getTimeRequired, provides the method to demonstrate the");
      System.out.println(" pattern. For branch nodes, the method will be passed on");
      System.out.println(" to the children. For terminal nodes (Deliverables), a");
      System.out.println(" single value will be returned.");
      System.out.println();
      System.out.println("Note that it is possible to make this method call ANYWHERE");
      System.out.println(" in the tree, since all classes implement the getTimeRequired");
      System.out.println(" method. This means that you are able to calculate the time");
      System.out.println(" required to complete the whole project OR any part of it.");
      System.out.println();

      System.out.println("Deserializing a test composite.Project for the Composite pattern");
      System.out.println();
      if (!(new File("data.ser").exists()))
      {
         DataCreator.serialize("data.ser");
      }
      Project project = (Project) (DataRetriever.deserializeData("data.ser"));

      System.out.println("Calculating total time estimate for the project");
      System.out.println("\t" + project.getDescription());
      System.out.println("Time Required: " + project.getTimeRequired());
   }
}
