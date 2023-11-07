package app;

import templatemethodtest.Deliverable;
import templatemethodtest.Task;

public class TemplateMethodTest
{
   public static void main(String[] args)
   {
      System.out.println("Example for the Template Method pattern");
      System.out.println("This code demonstrates how the template method can");
      System.out.println(" be used to define a variable implementation for a");
      System.out.println(" common operation. In this case, the ProjectItem");
      System.out.println(" abstract class defines the method getCostEstimate,");
      System.out.println(" which is a combination of the cost for time and");
      System.out.println(" materials. The two concrete subclasses used here,");
      System.out.println(" Task and Deliverable, have different methods of");
      System.out.println(" providing a cost estimate.");
      System.out.println();

      System.out.println("Creating a demo Task and Deliverable");
      System.out.println();
      Task primaryTask = new Task("Put a JVM on the moon", "Lunar mission as part of the JavaSpace program ;)",
                                  240.0, 100.0);
      primaryTask.addProjectItem(new Task("Establish ground control", "", 1000.0, 10.0));
      primaryTask.addProjectItem(new Task("Train the Javanaughts", "", 80.0, 30.0));
      Deliverable deliverableOne = new Deliverable("Lunar landing module",
                                                   "Ask the local garage if they can make a few minor modifications to one of their cars",
                                                   2800, 40.0, 35.0);

      System.out.println("Calculating the cost estimates using the Template Method, getCostEstimate.");
      System.out.println();
      System.out.println("Total cost estimate for: " + primaryTask);
      System.out.println("\t" + primaryTask.getCostEstimate());
      System.out.println();

      System.out.println("Total cost estimate for: " + deliverableOne);
      System.out.println("\t" + deliverableOne.getCostEstimate());
   }
}
